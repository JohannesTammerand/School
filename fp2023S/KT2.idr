%default total
 
-- 1) leia kõik reedeksid
 
-- (λa. (λb. c) a a) ((λx.x) c)
 
 
-- 2) beeta-redutseeri normaalkujule normaal- ja aplikatiivjärjekorras
 
-- (λf. f f) ((λx.x) (λx.b))
 
 
-- 3) tüübi tuletamine
-- "Joonista" tüübituletuspuu, kirjuta välja kõik kitsendused ja
-- lahenda kogu avaldise tüüp ɣ2.
-- 
-- Lihtsustuseks:
--  * ei pea kirjutama xᵅ ∈ Γ
--  * kitsendusi ei pea kirjutama puu sisse  (mis oli slaididel roheline)
-- 
 
-- ⊢ (λxᵅ¹. ((λyᵅ². (λzᵅ³. zᵅ⁴)ˠ¹)ˠ³ xᵅ⁵)ᵝ¹)ˠ²
 
 
 
 
-- 4) sõltuvate tüüpidega programmeerimine

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


data Vect : Nat -> Type -> Type where
    Nil  : Vect 0 a
    (::) : a -> Vect n a -> Vect (1+n) a
 
fromNat2 : Nat -> TreeShape
fromNat2 0 = LeafShape
fromNat2 (S k) = NodeShape (fromNat2 k) LeafShape

vecToTree2 : Vect n a -> Tree (fromNat2 n) a
vecToTree2 [] = Leaf
vecToTree2 (x::xs) = Node (vecToTree2 xs) x Leaf

-- vecToTree2 [] == the (Tree LeafShape Nat) Leaf
-- vecToTree2 [2] == Node Leaf 2 Leaf
-- vecToTree2 [1,2,3] == Node (Node (Node Leaf 3 Leaf) 2 Leaf) 1 Leaf

-- 5) tõestamine Idris2-s
 
infixl 10 /\
data (/\) : Type -> Type -> Type where
    ConI : a -> b
        ------
        -> a /\ b
 
infixl 11 \/
data (\/) : Type -> Type -> Type where
    DisjIl :   a
            ------
        -> a \/ b
 
    DisjIr :   b
            ------
        -> a \/ b
 
VoidE : Void
        ----
    ->    b
VoidE q impossible

not : Type -> Type 
not a = a -> Void
 
decidable : Type -> Type
decidable a = a \/ not a 

doubleNegElim : Type -> Type
doubleNegElim a = not (not a) -> a

peirce : Type -> Type -> Type
peirce p q = ((p -> q) -> p) -> p

-- Tõesta väide:  decidable a -> peirce a b

test2 : decidable a -> peirce a b
test2 (DisjIl x) = \f => x
test2 (DisjIr x) = ?auk


-- 6) lineaarsed tüübid

-- Kvantitatiivses tüübisüsteemis on arvud 0, 1 ja suva. 
-- Üks võimalus saada ülejäänud naturaalarve on teha arvuga 1 ressurssidest vektor pikkusega n.

-- abifunktsioon testimiseks
add10 : (1 _ : Nat) -> Nat
add10 x = case x of { Z => 10; S z => 11+z }

namespace Lin
    public export
    data Vect : Nat -> Type -> Type where
        Nil  : Lin.Vect 0 a
        (::) : (1 _ : a) -> (1 _ : Lin.Vect n a) -> Lin.Vect (S n) a

-- Implementeeri funktsioon appNth, mis rakendab lineaarset funktsiooni n-ndale lineaarse listi elemendile.
 
appNth : ((1 _ :a)->a) -> (n:Nat) -> (1 _: Lin.Vect (1+n+m) a) -> Lin.Vect (1+n+m) a
appNth f n Nil = ?rhs_appNth
appNth f 0 (x::xs)= (f x) :: xs
appNth f (S n) (x::xs) = ?rhs_appNth2 --appNth f n xs

-- appNth add10 2 [1,2,3,4] == [1, 2, 13, 4]
-- appNth add10 3 [1,2,3,4] == [1, 2, 3, 14]
-- appNth add10 0 [1,2,3,4] == [11, 2, 3, 4]