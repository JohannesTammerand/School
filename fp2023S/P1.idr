module P1

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

hanoi: Int -> Int
hanoi 1 = 1
hanoi n = 2 * hanoi(n-1) + 1

ack: Int -> Int -> Int
ack 0 n = n + 1
ack m n = if m > 0 && n == 0 then ack (m-1) 1 else ack (m-1) (ack m (n-1))

aste: Int -> Int -> Int
aste x 0 = 1
aste x 1 = x
aste x n = aste (x*x) (n-1)

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