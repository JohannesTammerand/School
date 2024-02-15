import Data.List
import System.Random

-- 1. substitutsioon

-- Tehke järgnevad substitutsioonid:
-- a. (𝜆𝑓.𝑓𝑦(𝜆𝑥.𝑥))[𝑦→𝜆𝑥𝑦.𝑓𝑥]
-- Vastus: 𝜆z.z(𝜆xy.fx)(𝜆x.x)
-- b. (𝜆𝑥.𝑓(𝑥𝑥))(𝜆𝑥.𝑓(𝑥𝑥))[𝑓→𝜆𝑥𝑦.𝑦]
-- Vastus: (𝜆x.(𝜆xy.y)(xx))(𝜆x.(𝜆xy.y)(xx))
-- c. (𝜆𝑥𝑔𝑦. 𝑥𝑔𝑦)[𝑔→𝑥𝑔𝑦]
-- Vastus: 𝜆z.z


-- 2. foldr

-- Ülesanne 2:
-- Kirjuta funkstsioon yl2, mis otsib argumentlistis paari, kus paari esimene 
-- komponent on True ja teine 0. Kui selline paar leidub, tagastatakse Just 
-- konstruktori all vähim sellise elemendi indeks -- mitmes element listis on 
-- sellisel kujul. Kui sellist paari ei leidu, tuleb tagastada Nothing.

--Funktsioon annab errori, ei saa Maybe Nat-ile liita Nat-i
yl2 : List (Bool, Int) -> Maybe Nat
yl2 = ?rhs_yl2 --if foldr f (Just 0) > 0 then foldr f (Just 0) else Nothing
    where f : (Bool, Int) -> Maybe Nat -> Maybe Nat
          f (x, y) z = ?rhs_f --if y == 0 && x == True then z else Just (plus z 1)

-- Näiteks:
-- yl2 [] == Nothing
-- yl2 [(True, 0)] == Just 0
-- yl2 [(False, 0)] == Nothing
-- yl2 [(True, 1)] == Nothing
-- yl2 [(False, 0),(False, 1),(True, 1),(True, 0),(True, 0)] == Just 3


-- 3. liidesed

-- Kirjuta järgneva liidese instantsid nii, et võrdused kehtiksid!

interface Veider x where
    ff : List x -> x

Veider Integer where
    ff [] = 0
    ff [x] = x
    ff (x::xs) = x + ff xs

Veider Bool where
    ff [] = False
    ff [x] = x
    ff (x::xs) = x && ff xs

-- Võrdused:
-- ff [2]     == 2
-- ff [100,3] == 103
-- ff [True,False] == False
-- ff [True,True]  == True


-- 4. Puud

-- Jõuluvana armastab väga puid ja seetõttu hoiab ta ka laste kohta 
-- andmeid puudes. Ta teab, kes on olnud üleannetu ja kes hea!
-- 
-- a) Laste nimede puust on vaja tekitada nimekiri. 
--   (Et seda siis kaks korda üle kontrollida!)
--
-- b) Jõuluvanal on vaja sorteerida puust välja kõik head lapsed.
--  Kirjuta funktsioon nice, mis jätab puu struktuuri samaks aga 
--  eemaldab üleannetute laste sissekanded.
 

data Tree a = LeafJust a | LeafNothing | Branch (Tree a) (Tree a)

Eq a => Eq (Tree a) where
    (LeafJust x) == (LeafJust y) = x==y
    LeafNothing  == LeafNothing  = True
    (Branch x z) == (Branch y w) = x==y && z==w
    _            == _            = False

data Descr = Naughty | Nice 

Eq Descr where
    Naughty == Naughty = True
    Nice    == Nice    = True
    _       == _       = False

-- toList (Branch (Branch (LeafJust "x") LeafNothing)  (Branch LeafNothing   (LeafJust "y"))) == ["x", "y"]
-- toList (Branch (Branch (LeafJust "x") (LeafJust "a")) (Branch (LeafJust "b")   (LeafJust "y"))) == ["x","a","b","y"]

-- nice (Branch LeafNothing (LeafJust ("Tiiu",Nice))) == Branch LeafNothing (LeafJust "Tiiu") 
-- nice (Branch LeafNothing (LeafJust ("Mari",Naughty))) == Branch LeafNothing LeafNothing
-- nice (Branch (LeafJust ("Mari",Naughty)) (LeafJust ("Tiiu",Nice))) ==      Branch LeafNothing (LeafJust "Tiiu")


nice : Tree (String,Descr) -> Tree String
nice LeafNothing = LeafNothing
nice (LeafJust (a, b)) = if b == Nice then LeafJust a else LeafNothing
nice (Branch x y) = Branch (nice x) (nice y)

toList : Tree String -> List String
toList LeafNothing = []
toList (LeafJust a) = [a]
toList (Branch x y) = (toList x) ++ (toList y)

-- 5. Juhuarvud

-- Protseduur yl5 n m genereerib n juhuarvu nullist m-ni ja tagastab 
-- genereeritud arvude aritmeetilise keskmise.

yl5 : Int -> Int32 -> IO Double
yl5 n m = ?rhs_yl5 --div (sum [randomRIO (0,m) | x <- [1..n]]) (cast n)

-- Main> :exec yl5 1 30 >>= printLn
-- 12.0
-- Main> :exec yl5 1 30 >>= printLn
-- 8.0
-- Main> :exec yl5 10000 30 >>= printLn
-- 14.9808
-- Main> :exec yl5 10000 30 >>= printLn
-- 15.0275