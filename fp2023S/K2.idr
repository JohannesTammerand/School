module K2
import Data.Monoid.Exponentiation

fst: (a, b) -> a
fst(x, y) = x

length: List a -> Int
length [] = 0
length(x::xs) = 1 + length xs

%hide Prelude.Types.List.(++)
infixr 7 ++
(++): List a -> List a -> List a
[] ++ ys = ys
(x::xs) ++ ys = x :: (xs ++ ys)

replicate: Int -> a -> List a
replicate 0 x = []
replicate n x = x :: (replicate (n-1) x)

take: Int -> List a -> List a
take n [] = []
take 0 a = []
take n (a::as) = a :: (take (n-1) as)

sum: List Integer -> Integer
sum [] = 0
sum (x::xs) = x + (sum xs)

drop: Int -> List a -> List a
drop 0 x = x
drop n [] = []
drop n (x::xs) = drop (n-1) xs

%hide Prelude.Types.List.reverse
reverse: List a -> List a
reverse [] = []
reverse (a::as) = (reverse as) ++ [a]

esimesed: List (a, b) -> List a
esimesed [] = []
esimesed((x, y) :: xs) = x :: esimesed(xs)

otsi: Integer -> List Integer -> Bool
otsi n [] = False
otsi n (x::xs) = if x == n then True else otsi n xs

dropLast: List a -> List a
dropLast [] = []
dropLast [x] = []
dropLast (x::xs) = x :: dropLast xs

lisa': Int -> Char -> List Char -> List Char
lisa' n a s = 
    if n <= 0 then a :: s else 
    case s of
        [] => [a]
        (y::ys) => y :: (lisa' (n-1) a ys)

lisa: Int -> Char -> String -> String
lisa i x ys = pack (lisa' i x (unpack ys))

arvuta: List (Double, Nat) -> Double -> Double
arvuta [] x = 0
arvuta ((k, a)::xs) x = k*(x^a) + arvuta xs x

lines': List Char -> List (List Char)
lines' [] = []
lines' (x) = [[x]]
lines' (x::y::xs) = if x == '\' & y == 'n' then (lines' (y::xs)) else ((x::(lines' (y::xs))))

lines: String -> List String
lines "" = []
lines x = lines'(unpack(x))