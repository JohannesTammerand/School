import System.Random

t2ring: IO ()
t2ring =  do
    r <- randomRIO (the Int32 1, 6)
    putStrLn (show r)

dialoog: IO ()
dialoog = do
    putStrLn "Mis on su nimi?"
    n <- getLine
    putStrLn ("Tere, " ++ n ++ "!")

prindiArvud: List Int32 -> IO ()
prindiArvud [] = putStrLn "Mitte midagi?"
prindiArvud [x] = putStrLn (show x)
prindiArvud (x::xs) = do
    putStrLn (show x)
    prindiArvud xs

readMaybe : IO (Maybe Int32)
readMaybe = do
  input <- getLine
  if all isDigit (unpack input)
    then pure (Just (cast input))
    else pure Nothing

loeArv: IO (Maybe Int32)
loeArv = do
    putStrLn "Anna arv"
    input <- getLine
    if all isDigit (unpack input)
        then pure (Just (cast input))
        else do
            putStrLn "See pole arv"
            loeArv

summa2: IO ()
summa2 = do
    putStrLn "Anna esimene arv"
    x <- getLine
    putStrLn "Nüüd anna teine arv"
    y <- getLine
    putStrLn (show (cast x + cast y))

summaN1: IO ()
summaN1 = do
    putStrLn "Anna arv"
    input <- getLine
    putStrLn (show (summaN1' (cast input)))
        where
            summaN1': Int32 -> Int32
            summaN1' 0 = 0
            summaN1' x = x + summaN1' (x-1)

{-
summaN2: IO()
summaN2 = do
    putStrLn "Anna arv"
    input <- getLine
    sequence [putStrLn (show x) | x <- [1..(cast input)]]
    putStrLn input
-}

m2ng: IO ()
m2ng = do
    putStrLn "Arva ära täisarv vahemikus nullist sajani!"
    x <- randomRIO(the Int32 0, 100)
    m2ng' x 1
        where
            m2ng': Int32 -> Int32 -> IO ()
            m2ng' x y = do
                input <- getLine
                case (x-cast input) of
                    0 => putStrLn ("Ära arvasid! Oligi " ++ (show x) ++ "! Arvasid " ++ (show y) ++ " Korda!")
                    z => if (z > 0 )
                        then do
                            putStrLn "Ei! Minu arv on suurem"
                            m2ng' x (y+1)
                        else do
                            putStrLn "Ei! Minu arv on väiksem"
                            m2ng' x (y+1)