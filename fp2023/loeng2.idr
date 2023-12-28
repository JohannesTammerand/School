--Programmis definitsioonid, igal definitsioonil oma tüüp

a : Double
a = 40.0

b : Double
b = 30.0

c : Double
c = sqrt(a*a + b*b)

--Definitsioone saab lugeda cmd-s

liida: Int -> Int -> Int
liida x y = x + y

liidaKaks: Int -> Int
liidaKaks = liida 2

f1 : Bool -> Int
f1 True = 1
f1 False = 2

f2 : (Int, Char, Bool) -> Int
f2 (x, y, z) = x + 1