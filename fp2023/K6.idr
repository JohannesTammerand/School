module K6

import Data.Nat

data Tree a = Leaf | Branch (Tree a) a (Tree a)
 
Eq a => Eq (Tree a) where
  Leaf == Leaf = True
  (Branch x y z) == (Branch w v s) =  x==w && y==v && z==s
  _ == _ = False
 
Functor Tree where
    map f Leaf = Leaf
    map f (Branch x y z) = Branch (map f x) (f y) (map f z)

Foldable Tree where
    foldr f b Leaf = b
    foldr f b (Branch x y z) = foldr f (f y (foldr f b z)) x

len: Foldable t => t a -> Int
len x = foldr (\x => (+1)) 0 x


infix 7 :/:
data Rat = (:/:) Nat Nat
 
-- normaliseerimine
norm : Rat -> Rat
norm (_   :/:   0) = 0 :/: 0
norm (0   :/:   _) = 0 :/: 1
norm (S a :/: S b) =
    let n = gcd (S a) (S b) in
    (S a) `div` n :/: (S b) `div` n
 
-- muud operatsioonid:
-- (a :/: b) == (c :/: d) = a*d == b*c
-- (a :/: b) +  (c :/: d) = a*d + b*c :/: b*d
-- (a :/: b) *  (c :/: d) = a*c :/: b*d
-- (a :/: b) /  (c :/: d) = a*d :/: b*c
-- pöörd (a :/: b) = b :/: a
 
neljandik : Rat
neljandik = 1 :/: 4
 
pool : Rat
pool = 1 :/: 2

Eq Rat where
    (==) (a :/: b) (c :/: d) = a*d == b*c

Num Rat where
    (+) (a :/: b) (c :/: d) = if b == d then (a+c :/: b) else ((a*d + c*b) :/: b * d)
    (*) (a :/: b) (c :/: d) = a*c :/: b*d
    fromInteger x = (fromInteger x :/: 1)

Fractional Rat where
    (/) (a :/: b) (c :/: d) = a*d :/: b*c
    recip (a :/: b) = b :/: a