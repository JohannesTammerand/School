punkt2d : Type
punkt2d = (Double, Double)

punkt : Nat -> Type
punkt Z     = Unit
punkt (S Z) = Double
punkt (S n) = (Double, punkt n)

nullpunkt : (d : Nat) -> punkt d
nullpunkt 0 = ()
nullpunkt (S 0) = 0
nullpunkt (S (S k)) = (0, nullpunkt (S k))

add: (d:Nat) -> punkt d -> punkt d -> punkt d
add 0 () () = ()
add (S 0) (x) (y) = x + y
add (S (S k)) (x, xs) (y, ys) = (x+y, add (S k) xs ys)

%hide Prelude.sum
sum: (d:Nat) -> List (punkt d) -> punkt d
sum 0 x = ()
sum (S 0) [] = 0
sum (S (S k)) [] = (0, (sum (S k) []))
sum (S 0) (x::xs) = x + sum (S 0) xs
sum (S (S k)) (x::xs) = add (S (S k)) x (sum (S (S k)) xs)


data Vect : (k : Nat) -> (a : Type) -> Type where
  Nil  : Vect Z a
  (::) : a -> Vect k a -> Vect (S k) a
 
Eq a => Eq (Vect k a) where
    [] == []             = True
    (x :: y) == (z :: w) = x==z && y==w

append : Vect n a -> Vect m a -> Vect (n + m) a
append []        ys = ys
append (x :: xs) ys = x :: append xs ys

zipVect : Vect n a -> Vect n b -> Vect n (a,b)
zipVect [] [] = []
zipVect (x::xs) (y::ys) = (x, y) :: zipVect xs ys

replaceVect : a -> (n:Nat) -> Vect (1+n+k) a -> Vect (1+n+k) a
replaceVect x 0 (z::zs) = x :: zs
replaceVect x (S k) (z::zs) = z :: replaceVect x k zs

min : Nat -> Nat -> Nat
min 0 y = 0
min (S k) 0 = 0
min (S k) (S j) = S (min k j)

{-
takeVec : (n:Nat) -> {m:Nat} -> Vect m a -> Vect (min n m) a
takeVec 0 {m} xs = []
takeVec n 0 [] = []
takeVec (S n) {m} (x::xs) = x :: takeVec (min n m) xs
-]