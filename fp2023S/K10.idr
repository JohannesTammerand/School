-- ainult täielikud funktsioonid tagavad turvalisuse
%default total 
 
infixl 10 /\
data (/\) : Type -> Type -> Type where
    ConI : a -> b
           ------
        -> a /\ b
 
-- Väljaviimise reegleid ise defineerima ei pea aga saame nende paikapidavust kontrollida.
conEl : a /\ b
        ------
     ->   a
 
-- Reegel kehtib, kui saame anda definitsiooni.
conEl (ConI a b) = a
 
conEr : a /\ b
        ------
     ->   b
 
conEr (ConI a b) = b


infixl 11 \/
data (\/) : Type -> Type -> Type where
    DisjIl :   a
             ------
          -> a \/ b
 
    DisjIr :   b
             ------
          -> a \/ b
 
disjE : (a\/b)  ->  (a -> c)  ->  (b -> c) 
        ----------------------------------
    ->               c

disjE (DisjIl a) f g = f a
disjE (DisjIr a) f g = g a


%hide Not  -- juba std. teegis olemas
 
data Not : Type -> Type where
    NotI :   (a -> Void)
             -----------
          ->    Not a
 
NotE : Not a -> a
       ----------
    ->    Void

NotE (NotI f) = f
 
--data Void : Type where
---- meelega jäetud tühjaks
 
 
VoidE : Void
        ----
    ->    b
VoidE q impossible

ex1 :  a /\ (b -> c) /\ (a -> b)  ->  c
ex1 (ConI (ConI x z) y) = z (y x)

--(Vihje: kasuta lambdat)
ex2 : a \/ Not a -> (a -> b) \/ (b -> a)
ex2 (DisjIl x) = DisjIr (\y => x)
ex2 (DisjIr (NotI f)) = ?ex2_v_2

data Even : Nat -> Type where
    Even_Zero : --------
                 Even 0
 
    Even_Succ :    Even n
                ------------
              -> Even (2+n)
 
even4 : Even 4
even4 = Even_Succ (Even_Succ Even_Zero)
 
even8 : Even 8
even8 = Even_Succ (Even_Succ (Even_Succ (Even_Succ Even_Zero)))
 
plusEvenEven :  Even n -> Even m
               ------------------
             ->    Even (n+m)
plusEvenEven Even_Zero b = b
plusEvenEven (Even_Succ x) b = Even_Succ (plusEvenEven x b)
 
 
multEvenEven :  Even n -> Even m
               ------------------
             ->    Even (n*m)
multEvenEven Even_Zero y = Even_Zero
multEvenEven (Even_Succ x) b = ?multEvenEven_v

data Odd : Nat -> Type where
    Odd_one : --------
               Odd 1
 
    Odd_Succ :    Odd n
                -----------
              -> Odd (2+n)
 
odd7 : Odd 7
odd7 = Odd_Succ (Odd_Succ (Odd_Succ Odd_one))
 
evenOdd :   Even n
          ----------
        -> Odd (1+n)
evenOdd Even_Zero = Odd_one
evenOdd (Even_Succ x) = Odd_Succ (evenOdd x)
 
 
plusOddOdd :  Odd n  ->  Odd m
             -------------------
           ->     Even (n+m)
plusOddOdd Odd_one Odd_one = Even_Succ Even_Zero
plusOddOdd Odd_one (Odd_Succ x) = ?rhs_plusOddOdd --plusOddOdd Odd_one x
plusOddOdd (Odd_Succ x) b = Even_Succ (plusOddOdd x b)
 
 
plusEvenOdd :  Even n  ->  Odd m
             -------------------
           ->     Odd (n+m)
plusEvenOdd Even_Zero b = b
plusEvenOdd (Even_Succ x) b = Odd_Succ (plusEvenOdd x b)
 
plusNullOdd :    Odd m
              ------------
            -> Odd (m+0)
plusNullOdd Odd_one = Odd_one
plusNullOdd (Odd_Succ x) = Odd_Succ (plusNullOdd x)

plusNull :   (n:Nat)
            ---------
          -> n+0 = n
plusNull 0 = Refl
plusNull (S k) = rewrite plusNull k in Refl

plusAssoc : (m:Nat) -> (n:Nat) -> (q:Nat)
            ------------------------------
          ->  m + (n + q) = (m + n) + q
plusAssoc 0 0 0 = Refl
plusAssoc (S k) 0 0 = rewrite plusAssoc k 0 0 in Refl
plusAssoc m (S k) 0 = rewrite plusAssoc m k 0 in Refl
plusAssoc m n (S k) = rewrite plusAssoc m n k in Refl