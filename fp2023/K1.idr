module K1

sumInt: Int -> Int
sumInt 0 = 0
sumInt n = sumInt(n - 1) + n

fib: Int -> Int
fib 0 = 0
fib 1 = 1
fib n = fib (n-1) + fib(n-2)

modulo: Int -> Int -> Int
modulo x y = if x < y then x else modulo(x-y) y

syt: Int -> Int-> Int
syt x y = if y == 0 then x else syt y (modulo x y)

mc: Int -> Int
mc x = if x > 100 then x - 10 else mc (mc (x+11))

hanoi: Int -> Int
hanoi 1 = 1
hanoi n = 2 * hanoi(n-1) + 1

ack: Int -> Int -> Int
ack 0 n = n + 1
ack m n = if m > 0 && n == 0 then ack (m-1) 1 else ack (m-1) (ack m (n-1))

korda: Int -> (Int -> Int) -> Int -> Int
korda 0 f x = x
korda n f x = f (korda (n - 1) f x)
 
inc: Int -> Int
inc x = x + 1

add: Int -> Int -> Int
add x y = korda x inc y

mul: Int -> Int -> Int
mul x 0 = 0
mul x y = add x (mul x (add y (-1)))

aste: Int -> Int -> Int
aste x 0 = 1
aste x 1 = x
aste x n = aste (x*x) (n-1)

p: Int -> Int -> Int
p n k = if n-1 == n-k-1 then n * (n-1) else n * (p (n-1) k)

c: Int -> Int -> Int
c n 0 = 1
c n k = if n == k then 1 else c (n-1) k + c (n-1) (k-1)

qaste: Int -> Int -> Int
qaste x 0 = 1
qaste x n = if (n `mod` 2) == 0 then (y x n)*(y x n) else x*(y x n)*(y x n)
    where
        y: Int -> Int -> Int
        y x n = qaste x (n `div` 2)

ndiv: Int -> Int -> Int
ndiv x y =
    let f: Int -> Int -> Int
        f n z = if n < y then z else f (n-y) (z+1)
    in f x 0

