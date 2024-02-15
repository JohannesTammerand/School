import Data.IOArray
import Data.List

data TreeShape : Type where
  LeafShape : TreeShape
  NodeShape : (l : TreeShape) -> (r : TreeShape) -> TreeShape
 
data Tree : TreeShape -> Type -> Type where
  Leaf : Tree LeafShape a
  Node : (left : Tree l a) -> (this : a) -> (right : Tree r a) ->
       Tree (NodeShape l r) a
 
Eq TreeShape where
    LeafShape == LeafShape  = True
    LeafShape == (NodeShape l r)  = False
    (NodeShape l r) == LeafShape  = False
    (NodeShape l r) == (NodeShape x y)  =
        l==x && r==y
 
Eq a => Eq (Tree s a) where
    Leaf == Leaf = True
    (Node left this right) == (Node x y z) =
        left == x && this == y && right == z
 
tr1 : Tree (NodeShape LeafShape (NodeShape LeafShape LeafShape)) Int
tr1 = Node Leaf 1 (Node Leaf 2 Leaf)
 
tr2 : Tree (NodeShape LeafShape (NodeShape LeafShape LeafShape)) Int
tr2 = Node Leaf 3 (Node Leaf 1 Leaf)

zip_tree : Tree shape a -> Tree shape b -> Tree shape (a, b)
zip_tree Leaf Leaf = Leaf
zip_tree (Node left this right) (Node x y z) = Node (zip_tree left x) (this, y) (zip_tree right z)

flip_shape : TreeShape -> TreeShape
flip_shape LeafShape = LeafShape
flip_shape (NodeShape l r) = (NodeShape (flip_shape r) (flip_shape l))

flip_tree : Tree shape a -> Tree (flip_shape shape) a
flip_tree Leaf = Leaf
flip_tree (Node left this right) = Node (flip_tree right) this (flip_tree left)



namespace Massiivid
    %hide Builtin.(#)
    %hide Builtin.DPair.(#)
    infix 5 #
 
    public export
    data L : Type -> Type -> Type where
        (#) : (1 _ : a) -> b -> L a b
 
    export
    interface Array arr where
      write  : (1 a : arr) -> Nat -> Double -> arr
      read   : (1 a : arr) -> Nat -> L arr Double
      size   : (1 a : arr) -> L arr Nat
      withArray : Nat -> ((1 a : arr) -> L arr c) -> c     
 
 
    export
    Array (List Double) where
        write [] n d = []
        write (x :: xs) 0 d = d :: xs
        write (x :: xs) (S k) d = x :: write xs k d
 
        read [] n = [] # 0.0
        read (x :: xs) 0  = (x::xs) # x
        read (x :: xs) (S n) =
            let _ # r = read xs n in
            x::xs # r
 
        size []        = [] # 0
        size (x :: xs) = (x :: xs) # (length (x :: xs))
 
        withArray l f =
            let _ # r = f (replicate l 0.0) in
            r
 
    export
    data LinArray = MkLinArray (IOArray Double)
 
    private
    newIOArr : List Double -> IO (IOArray Double)
    newIOArr xs =
        let l = cast (length xs) in
        do a <- newArray l
           let upd = zip [0..l-1] xs
           traverse_ (uncurry $ writeArray a) upd
           pure a
 
    export
    Array LinArray where
        withArray n f =
            unsafePerformIO (do a <- newArray (cast n)
                                let (_ # r) = f (MkLinArray a)
                                pure r )
 
        size (MkLinArray a) = MkLinArray a # (cast (max a))
 
        write (MkLinArray a) i v =
            unsafePerformIO (do ok <- writeArray a (cast i) v
                                pure (MkLinArray a))
        read (MkLinArray a) i =
            unsafePerformIO (do r <- readArray a (cast i)
                                case r of
                                    Just v  => pure (MkLinArray a # v)
                                    Nothing => pure (MkLinArray a # 0))

writeAllHelper : Array arr => (1 _ : arr) -> List Double -> Nat -> Nat -> arr
writeAllHelper a [] n i = a
writeAllHelper a (x::xs) n i = 
  if n > i then
    a
  else
    let a1 = write a i x
    in writeAllHelper a1 xs n (S i)

writeAll : Array arr => (1 _ : arr) -> List Double -> Nat -> arr
writeAll a xs n = writeAllHelper a xs n 0

toLstHelper : Array arr => (1 _ : arr) -> Nat -> Nat -> Nat -> L arr (List Double)
toLstHelper a s f i =
  let a # l = size a
  in if i >= l then
    a # []
    else let a # lst = toLstHelper a s f (S i) in
      case (i <= f , i >= s) of
        (True,  True) =>
          let a # iv = read a i
          in a # (iv :: lst)
        (True, False) =>
          a # lst
        (_, _) =>
          a # []

toLst : Array arr => (1 _ : arr) -> Nat -> Nat -> L arr (List Double)
toLst a s f = toLstHelper a s f 0

swap : Array arr => (1 _ : arr) -> Nat -> Nat -> arr
swap a i j = let
  a1 # iv = read a i
  a2 # jv = read a1 j
  a3 = write a2 i jv
  in write a3 j iv

-- pseudokood:
-- loop a pivot j hi i =
--   while (j>=hi){
--      if (a[j] < pivot) {
--          swap a[i] a[j]   
--          i++
--      }
--      j++
--   }
--   return i
 
 
loop: Array arr => (1 a : arr) -> (pivot:Double) -> (j:Nat)
                -> (hi:Nat) -> (i:Nat) -> L arr Nat
loop a pivot j hi i =
    if j > hi then a # i else
        let a # aj = read a j in
        if aj < pivot then
            let a = Main.swap a i j in
            loop a pivot (j+1) hi (i+1)
        else
            loop a pivot (j+1) hi i
 
-- pseudokood:
-- partition a lo hi =
--    pivot = a[hi]                  // pivot on viimane element
--    i = loop a pivot lo (hi-1) lo  // tsükkel, kus viimast elementi ei vaata
--    swap a[i] a[hi]                // nüüd vahetame viimase elemendi
--    return i                       // tagastame pivot-i indeksi
 
-- abiks Nat-i vähenamine
decr : Nat -> Nat
decr 0 = 0
decr (S n) = n
 
partition : Array arr => (1 a : arr) -> (lo: Nat) -> (hi: Nat) -> L arr Nat
partition a lo hi =
    let a # pivot = read a hi
        a # i     = loop a pivot lo (decr hi) lo
        a         = Main.swap a i hi
    in a # i
 
 
-- pseudokood:
--  qs a lo hi =
--    if (lo<hi) {
--       pivot_index = partition a lo hi
--       qs a lo (pivot_indeks - 1)
--       qs a (pivot_indeks + 1) hi
--    }
--    return a                 
 
qs : Array arr => (1 a : arr) -> (lo: Nat) -> (hi: Nat) -> arr
qs a lo hi =
    if (lo>=hi) then a else
    let a # pi = partition a lo hi
        a      = qs a lo (decr pi)
        a      = qs a (1 + pi) hi
    in a
 
-- sorteerib kogu massiivi
quickSortArray : Array arr => (1 _ : arr) -> arr
quickSortArray a =
    let a # len = size a in
    qs a 0 (decr len)


quickSortList : List Double -> List Double
quickSortList xs = withArray len f
        where len : Nat
              len = length xs
              f : (1 _ : LinArray) -> L LinArray (List Double)
              f a = ?rhs_quickSortList
 
testList : List Double
testList = map cast $ concat [[20,18..0],[1,3..19]]