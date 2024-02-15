import Data.List
-- elem
import Data.SortedSet
-- empty, singleton, insert, delete, contains, union
 
data Lam = Var String | App Lam Lam | Abs String Lam
 
showLam : Nat -> Lam -> String
showLam _ (Var x)   = x
showLam d (App f e) = showParens (d>1) (showLam 1 f++" "++showLam 2 e)
showLam d (Abs x e) = showParens (d>0) ("\\ "++x++". "++showLam 0 e)
 
-- See on liides -- liideseid vaatame järgmises praksis.
Show Lam where
    show = showLam 0
 
-- t1 := \f. (\g. g x) (\x. f x)
t1 : Lam
t1 = Abs "f" (App (Abs "g" (App (Var "g") (Var "x"))) (Abs "x" (App (Var "f") (Var "x"))))
 
-- t2 := (\x. (\g. g x)) x
t2 : Lam
t2 = App (Abs "x" (Abs "g" (App (Var "g") (Var "x")))) (Var "x")
 
-- t3 := \g. (\f. f x) (\x. g x)
t3 : Lam
t3 = Abs "g" (App (Abs "f" (App (Var "f") (Var "x"))) (Abs "x" (App (Var "g") (Var "x"))))

fv: Lam -> SortedSet String
fv (Var x) = singleton x
fv (App x y) = union (fv x) (fv y)
fv (Abs x y) = if contains x (fv y) then delete x (fv y) else fv y

fvList: Lam -> List String
fvList = SortedSet.toList . fv

subst: Lam -> String -> Lam -> Lam
subst (Var y) x e = if x == y then e else Var y
subst (App e1 e2) x e = App (subst e1 x e) (subst e2 x e)
subst (Abs y e1) x e = if x == y then Abs y e1 else if contains y (fv e) == False then Abs y (subst e1 x e) else subst (Abs "z" (subst e1 y (Var "z"))) x e

lamEq: List (String, String) -> Lam -> Lam -> Bool
lamEq v (Var x) (Var y) = elem (x, y) v
lamEq v (App e1 e2) (App e3 e4) = lamEq v e1 e3 && lamEq v e2 e4
lamEq v (Abs x e1) (Abs y e2) = lamEq ([(u, v)|(u, v) <- v, x /= u, y /= v] ++ [(x, y)]) e1 e2
lamEq _ (Var _) (App _ _)   = False
lamEq _ (Var _) (Abs _ _)   = False
lamEq _ (App _ _) (Var _)   = False
lamEq _ (App _ _) (Abs _ _) = False
lamEq _ (Abs _ _) (Var _)   = False
lamEq _ (Abs _ _) (App _ _) = False

Eq Lam where
    x == y = lamEq [(x,x)| x <- fvList x ++ fvList y] x y