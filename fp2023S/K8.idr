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
 
-- (\ x. x x)(\ x. x x)
inf : Lam
inf = Abs "x" (Var "x" @@ Var "x") @@ Abs "x" (Var "x" @@ Var "x")
 
succ : Lam
succ = Abs "n" (Abs "f" (Abs "x" (Var "n" @@ Var "f" @@ (Var "f" @@ Var "x"))))
 
add : Lam
add = Abs "m" (Abs "n" (Abs "f" (Abs "x"
            (Var "m" @@ Var "f" @@ (Var "n" @@ Var "f" @@ Var "x")))))
 
mul : Lam
mul = Abs "m" (Abs "n" (Abs "f" (Abs "x"
            (Var "m" @@ (Var "n" @@ Var "f") @@ Var "x"))))

-- \m n. \ f x. n m f x
expr: Lam
expr = Abs "m" (Abs "n" (Abs "f" (Abs "x" (Var "n" @@ Var "m" @@ Var "f" @@ Var "x"))))

redA : Lam -> Lam
redA (App x y) = 
    case x of
        (Abs z c) => redA (subst c z y)
        _ => redA x @@ redA y
redA (Abs x y) = if contains x (freeVars y) then y else Abs x y
redA t = t