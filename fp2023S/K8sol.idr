import Data.SortedSet

data Lam  = Var String          -- muutuja
          | App Lam   Lam       -- rakendus
          | Abs String Lam      -- abstraktsioon

showLam : Nat -> Lam -> String
showLam _ (Var x)   = x
showLam d (App f e) = 
    showParens (d>1) (showLam 1 f++" "++showLam 2 e)
showLam d (Abs x e) = 
    showParens (d>0) ("\\ "++x++". "++showLam 0 e)

Show Lam where
    show = showLam 0

Eq Lam where
    (Var x)   == (Var y)   = x==y
    (App x y) == (App z w) = x==z&&y==w
    (Abs x y) == (Abs z w) = x==z&&y==w
    _         == _         = False

infixl 3 @@

(@@) : Lam -> Lam -> Lam
x @@ y = App x y

true : Lam 
true = Abs "x" (Abs "y" (Var "x"))

false : Lam 
false = Abs "x" (Abs "y" (Var "y"))

cond : Lam 
cond = Abs "t" (Abs "x" (Abs "y" (Var "t" @@ Var "x" @@ Var "y")))


num : Nat -> Lam
num n = Abs "f" (Abs "x" (app_f n)) where 
    app_f : Nat -> Lam
    app_f 0 = Var "x"
    app_f (S k) = (Var "f") @@ (app_f k)
    
succ : Lam
succ = Abs "n" (Abs "f" (Abs "x" (Var "n" @@ Var "f" @@ (Var "f" @@ Var "x"))))

add : Lam
add = Abs "m" (Abs "n" (Abs "f" (Abs "x" 
            (Var "m" @@ Var "f" @@ (Var "n" @@ Var "f" @@ Var "x")))))

mul : Lam
mul = Abs "m" (Abs "n" (Abs "f" (Abs "x" 
            (Var "m" @@ (Var "n" @@ Var "f") @@ Var "x"))))

expr : Lam
expr = Abs "m" (Abs "n" (Abs "f" (Abs "x" 
            (Var "m" @@ Var "n" @@ Var "f" @@ Var "x"))))

-- (\ x. x x)(\ x. x x)
inf : Lam
inf = Abs "x" (Var "x" @@ Var "x") @@ Abs "x" (Var "x" @@ Var "x")


freeVars : Lam -> SortedSet String
freeVars (Var x)   = singleton x 
freeVars (App x y) = freeVars x `union` freeVars y
freeVars (Abs x y) = delete x (freeVars y)

subst : Lam -> String -> Lam -> Lam
subst (Var x) v r = if x==v then r else Var x
subst (App x y) v r = App (subst x v r) (subst y v r)
subst (Abs x y) v r = 
    if v==x then Abs x y 
    else if contains x (freeVars r) then subst (Abs x' (subst y x (Var x'))) v r
    else Abs x (subst y v r)
            where x' : String 
                  x' = x++"'"

-- Kirjuta funktsioon redA, mis redutseerib termi normaalkujule,
-- kasutades aplikatiivjärjekorda.

-- Vihje: Rakendamise  e1 e2  puhul redutseerime e1 normaalkujule.
-- Kui see on lambda, siis asenda lambda kehas termi e2 normaalkujuga 
-- ja redutseeri tulemust. Kui polnud lambda, siis jääb termiks 
-- funktsiooni rakendamine.

-- Lahendus:
redA : Lam -> Lam
redA (App x y) = 
    case redA x of
        Abs z t => redA (subst t z (redA y))
        nx      => App nx (redA y)
redA (Abs x y) = Abs x (redA y)
redA t = t

-- (show $ redA (cond @@ true @@ num 2 @@ num 3)) == "\\ f. \\ x. f (f x)"
-- (show $ redA (add @@ num 2 @@ num 3)) == "\\ f. \\ x. f (f (f (f (f x))))"
-- (show $ redA (cond @@ true @@ num 2 @@ inf))  -- ei termineeru

-- Kirjuta funktsioon redN, mis redutseerib termi normaalkujule,
-- kasutades normaaljärjekorda.

-- Vihje: Rakendamise  e1 e2  puhul redutseerime e1 normaalkujule.
-- Kui see on lambda, siis asenda lambda kehas termiga e2 ja redutseeri tulemus.
-- Kui pole lambda, siis jääb termiks funktsiooni rakendamine.


-- Lahendus (pole päris normaaljärjekord):
redN : Lam -> Lam    
redN (App x y) = 
    case redN x of
      Abs z t => redN (subst t z y)
      nx      => App nx (redN y)
redN (Abs x y) = Abs x (redN y)
redN t = t

-- Probleem on selles, et case-of-i sees olev redN väärtustab 
-- lambda all, kuid ei tohiks.

-- Lahendus:
-- Esiteks vajame abifunktsiooni, mis termi n.n. nõrgale peanormaalkujule.
-- See on nagu normaalkuju aga ei väärtusta lambda all ega rakenduse argumenti.
-- S.t. vaid beeta-reduktsioon ja lihtsustame vaid rakenduse funktsiooni osa
redNwhnf : Lam -> Lam    
redNwhnf  (App x y) = 
    case redNwhnf x of
      Abs z t => redNwhnf (subst t z y)
      nx      => App nx y
redNwhnf t = t

-- Praktilises keeltes rohkem vaja ei olegi. 
-- Aga Churchi numbrite evalueerimiseks peame viima päris normaalkujule ...

redN2 : Lam -> Lam    
redN2 (App x y) = 
    case redNwhnf x of
      Abs z t => redN2 (subst t z y)
      nx      => App nx (redN2 y)
redN2 (Abs x y) = Abs x (redN2 y)
redN2 t = t

-- show $ redN (cond @@ true @@ num 2 @@ num 3) == "\ f. \ x. f (f x)"
-- show $ redN (add @@ num 2 @@ num 3) == "\ f. \ x. f (f (f (f (f x))))"
-- show $ redN (cond @@ true @@ num 2 @@ inf)  == "\ f. \ x. f (f x)"

-- Väga hea aga tahame näha samme!

-- Kirjuta funktsioon redA1, mis teeb ühe reduktsioonisammu,
-- kasutades aplikatiivjärjekorda. Kui sammu teha ei saa, siis Nothing.

-- Vihje: Rakendamise  e1 e2  puhul tuleb lahendusse kolm üksteise sees olevat
-- case-of-i. S.t. esmalt proovime teha sammu e1-s, kui see ei õnnestu, 
-- siis e2-s. Kui e2-s ei õnnestu sammu teha, siis vaatame, kas see on lambda.

-- Lahendus:
redA1 : Lam -> Maybe Lam
redA1 (App x y) = 
    case redA1 x of
        Just xs => Just (App xs y)
        Nothing =>
            case redA1 y of
                Just ys => Just (App x ys)
                Nothing =>
                    case x of
                        Abs z t => Just (subst t z y)
                        nx      => Nothing
redA1 (Abs x y) = 
    case redA1 y of 
        Nothing => Nothing
        Just sy => Just (Abs x sy)
redA1 t = Nothing

-- (show (redA1 (num 3))) == "Nothing"
-- (show (redA1 (cond @@ false))) == "Just \ x. \ y. (\ x. \ y. y) x y"
-- (show (redA1 (succ @@ num 0))) == "Just \ f. \ x. (\ f. \ x. x) f (f x)"
-- (show (redA1 (add @@ (succ @@ (num 0)) @@ num 0))) == "Just (\ m. \ n. \ f. \ x. m f (n f x)) (\ f. \ x. (\ f. \ x. x) f (f x)) (\ f. \ x. x)"

-- testimiseks trükime välja kõi sammud.
printSteps : (Lam -> Maybe Lam) -> Lam -> IO ()
printSteps f l = 
    case f l of
        Nothing => printLn l
        Just l' => do printLn l
                      printSteps f l'

-- Main> :exec printSteps redA1 (cond @@ true @@ num 2 @@ num 3)
-- (\ t. \ x. \ y. t x y) (\ x. \ y. x) (\ f. \ x. f (f x)) (\ f. \ x. f (f (f x)))
-- (\ x. \ y. (\ x. \ y. x) x y) (\ f. \ x. f (f x)) (\ f. \ x. f (f (f x)))
-- (\ x. \ y. (\ y. x) y) (\ f. \ x. f (f x)) (\ f. \ x. f (f (f x)))
-- (\ x. \ y. x) (\ f. \ x. f (f x)) (\ f. \ x. f (f (f x)))
-- (\ y. \ f. \ x. f (f x)) (\ f. \ x. f (f (f x)))
-- \ f. \ x. f (f x)

-- Kirjuta funktsioon redN1, mis teeb ühe reduktsioonisammu,
-- kasutades normaaljärjekorda. Kui sammu teha ei saa, siis Nothing.

-- Rakendamise  @@e1 e2@@  puhul tuleb ka seekord lahendusse kolm üksteise sees olevat @@case-of@@-i. Aga nüüd kontrollime esmalt, kas @@e1@@ on lambda.

-- redN1 : Lam -> Maybe Lam
-- redN1 (App x y) = ?redN1_rhs1
-- redN1 (Abs x y) = ?redN1_rhs2
-- redN1 t = ?redN1_rhs3

-- Lahendus:
redN1 : Lam -> Maybe Lam
redN1 (App x y) = 
    case x of
        Abs z t => Just (subst t z y)
        nx      => 
          case redN1 x of
            Just xs => Just (App xs y)
            Nothing =>
                case redN1 y of
                    Just ys => Just (App x ys)
                    Nothing => Nothing
redN1 (Abs x y) = 
    case redN1 y of 
        Nothing => Nothing
        Just sy => Just (Abs x sy)
redN1 t = Nothing

-- (show (redN1 (num 3))) == "Nothing"
-- (show (redN1 (cond @@ false))) == "Just \ x. \ y. (\ x. \ y. y) x y"
-- (show (redN1 (succ @@ num 0))) == "Just \ f. \ x. (\ f. \ x. x) f (f x)"
-- (show (redN1 (add @@ (succ @@ (num 0)) @@ num 0))) == "Just (\ n. \ f. \ x. (\ n. \ f. \ x. n f (f x)) (\ f. \ x. x) f (n f x)) (\ f. \ x. x)"


-- Main> :exec printSteps redN1 (cond @@ true @@ num 2 @@ num 3)
-- (\ t. \ x. \ y. t x y) (\ x. \ y. x) (\ f. \ x. f (f x)) (\ f. \ x. f (f (f x)))
-- (\ x. \ y. (\ x. \ y. x) x y) (\ f. \ x. f (f x)) (\ f. \ x. f (f (f x)))
-- (\ y. (\ x. \ y. x) (\ f. \ x. f (f x)) y) (\ f. \ x. f (f (f x)))
-- (\ x. \ y. x) (\ f. \ x. f (f x)) (\ f. \ x. f (f (f x)))
-- (\ y. \ f. \ x. f (f x)) (\ f. \ x. f (f (f x)))
-- \ f. \ x. f (f x)

-- Evaluatsioon ehk väärtustamine tähistab meetodit, kus 
-- igale termile seatakse vastavusse (s.t. funktsioon) üks 
-- matemaatiline objekt. 

-- Nii tahame mööda hiilida substitutsioonidest.
-- Suurte termide teisendamine on aeglane!

-- Praktikas, kus kasutatakse konstantidega lambda-arvutust,
-- aplikatiivjärjekorda ja kinniseid terme, on see väga lihtne. 
-- Lambdad teisendatakse funktsioonideks ja konstandid konstantideks.

-- Puhta lambda-arvutuse puhul on vaja rohkem vaeva näha:
data Val = FVal (Val -> Val)    -- lambdade jaoks funktsioonid
         | GVar String          -- vabade muutujatejaoks globaalid
         | GApp Val Val         -- vabale muutujale rakenduse jaoks see
         | LazyVal (() -> Val)  -- laiskuse jaoks see

-- (aplikatiivjärjekorra jaoks ole LazyVal-i vaja)

-- Kuna puhta lambda-arvutuse väärtused on lambda-termid, 
-- peame väärtustamise järel tegema need jälle termideks.
-- Kuna muutujate nimed läksid kaotsi, genereerime uued.
var2lam : Nat -> Val -> (Lam, Nat)
var2lam n (LazyVal f) = var2lam n (f ())
var2lam n (GVar x) = (Var x, n)
var2lam n (GApp f x) = 
    let (fl,n1) = var2lam n f in
    let (xl,n2) = var2lam n1 x in
    (App fl xl, n2)
var2lam n (FVal f) = 
    let (b,n1) = var2lam (n+1) (f (GVar x)) in
    (Abs x b, n1)
        where x : String
              x = "x"++show n

-- Asendamise asemel kasutame seisundeid
data Env = Empty | InsertEnv String Val Env

lookupEnv : String -> Env -> Maybe Val
lookupEnv n Empty             = Nothing
lookupEnv n (InsertEnv x y z) = if n==x then Just y else lookupEnv n z

-- Näitena on toodud väärtustamine normaaljärjekorras. See on keerukam.
-- Hiljem on teie ülesandeks teha aplikatiivjärjekorras väärtustaja.

-- funktsioon, mis väärtustab laisad väärtused
evalLazy : Val -> Val
evalLazy (LazyVal f) = evalLazy (f ())
evalLazy t = t

-- normaaljärjekorras väärtustamine
evalN : Lam -> (Env -> Val)
evalN (Var x) env = 
    case lookupEnv x env of
        -- kohalike muutujate jaoks väärtused leiduvad
        Just e  => e    
        -- vabade muutujate jaoks mitte
        Nothing => GVar x
evalN (App f y) env = 
    -- esmalt peame väärtustama f-i, isegi kui see on laisk väärtus
    case evalLazy (evalN f env) of 
      -- kui f oli funktsioon, siis kutsume selle välja
      -- normaaljärjekorra puhul on argument LazyVal
      FVal xf => xf (LazyVal (\ () => evalN y env))
      -- kui f pole funktsioon, kasutame GApp-i
      -- normaaljärjekorra puhul on argument ikka LazyVal
      f       => GApp f (LazyVal (\ () => evalN y env))
evalN (Abs x y) env = 
    -- lambdale vastab funktsioon
    -- lambda kehas lisame argumendi seisundisse
    FVal (\ xv => evalN y (InsertEnv x xv env))

-- alustame tühja seisundiga ning muutujaid x0-ga 
-- hiljem viskame loenduri ära
evalNLam : Lam -> Lam
evalNLam e = fst (var2lam 0 (evalN e Empty))

-- show $ evalNLam (add @@ num 2 @@ num 3) == "\ x0. \ x1. x0 (x0 (x0 (x0 (x0 x1))))"
-- show $ evalNLam (cond @@ true @@ num 2 @@ inf) == "\ x0. \ x1. x0 (x0 x1)"

-- Teie ülesanne on kirjutada aplikatiivjärjekorras väärtustaja!
-- Sisuline erinevus on laiskuse puudumises. 
-- evalA : Lam -> (Env -> Val)
-- evalA (Var x) env =  ?evalA_rhs_1
-- evalA (App x y) env = ?evalA_rhs_2
-- evalA (Abs x y) env = ?evalA_rhs_3

-- -- Lahendus:
evalA : Lam -> (Env -> Val)
evalA (Var x) env = 
    case lookupEnv x env of
        Just e  => e
        Nothing => GVar x
evalA (App x y) env = 
    case evalA x env of 
      FVal xf => xf (evalA y env)
      f       => GApp f (evalA y env)
evalA (Abs x y) env = 
    FVal (\ xv => evalA y (InsertEnv x xv env))

evalALam : Lam -> Lam
evalALam e = fst (var2lam 0 (evalA e Empty))

-- Pange tähele!
-- 1. Evaluaatorid on väga kiired, lihtsad ja lühikesed.

-- 2. Praktiliste keelte jaoks pole enamasti var2lam-i vaja.

-- 3. Siin jõuab keelte teooria kompilaatorite praktikale peaaegu järele 
--    kuna FVal vastab genereeritud koodi-(viida)-le ja Env funktsiooni 
--    call-stack-(viida)le. 
