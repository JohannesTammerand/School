module K3
import Data.List

filter': (a -> Bool) -> List a -> List a
filter' f [] = []
filter' f (x::xs) = if f x == True then x :: filter' f xs else filter' f xs

nullid1: List Int -> Int
nullid1 [] = 0
nullid1 (x::xs) = if x == 0 then 1 + nullid1 xs else nullid1 xs

nullid2: List Int -> Int
nullid2 xs = foldr (\ x => if x == 0 then (+ 1) else (+ 0)) 0 xs

nullid3: List Int -> Int
nullid3 xs = sum (map (\x => if x == 0 then 1 else 0) xs)

nullid4: List Int -> Nat
nullid4 [] = 0
nullid4 xs = length (filter' (==0) xs)

nullid5: List Int -> Nat
nullid5 xs = length [x | x <- xs, x == 0]

length': List a -> Int
length' xs = foldl (\x, y => x + 1) 0 xs

productList: List Int -> Int
productList xs = foldr (*) 1 xs

append': List a -> List a -> List a
append' xs ys = foldr (::) ys xs

isEven : Nat -> Bool
isEven Z         = True
isEven (S Z)     = False
isEven (S (S n)) = isEven n

all': (a -> Bool) -> List a -> Bool
all' f xs = foldr (\x => (&& f x)) True xs

reverse' : List a -> List a
reverse' df = foldl rev [] df
  where
    rev : List a -> a -> List a
    rev x y = y :: x

eemaldaNullid: List Int -> List Int
eemaldaNullid df = foldr rem [] df
    where rem: Int -> List Int -> List Int
          rem x y = if x == 0 then y else x :: y

allEqual: List Int -> Bool
allEqual xs = ?rhs_allEqual --map (\x => x == head xs) xs
