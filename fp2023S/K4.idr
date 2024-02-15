module K4

data Email = E String String

varmo     : Email
kalmer    : Email
karoliine : Email
varmo     = E "varmo.vene"       "ut.ee"
kalmer    = E "kalmer.apinis"    "ut.ee"
karoliine = E "karoliine.holter" "ut.ee"

data Tree a = Leaf | Branch (Tree a) a (Tree a)

Eq a => Eq (Tree a) where
    (Branch Leaf x Leaf) == (Branch Leaf y Leaf) = x == y
    (Branch (Branch x1 y1 z1) x (Branch x2 y2 z2)) == (Branch (Branch x3 y3 z3) y (Branch x4 y4 z4)) = x == y &&  y1 == y2 && y3 == y4 && x1 == x2 && z1 == z2 && x3 == x4 && z3 == z4
    Branch Leaf _ Leaf == Branch Leaf _ (Branch _ _ _) = False
    Branch Leaf _ Leaf == Branch (Branch _ _ _) _ _ = False
    Branch Leaf _ Leaf == Leaf = False
    Branch Leaf _ (Branch _ _ _) == _ = False
    Branch (Branch _ _ _) _ (Branch _ _ _) == Branch (Branch _ _ _) _ Leaf = False
    Branch (Branch _ _ _) _ (Branch _ _ _) == Branch Leaf _ _ = False
    Branch (Branch _ _ _) _ (Branch _ _ _) == Leaf = False
    Branch (Branch _ _ _) _ Leaf == _ = False
    Leaf == _ = False

height: Tree a -> Int
height (Branch Leaf _ Branch) = 1
height (Branch x y z) = height x > height z