// UnsupportedOperationException tähistab, et meetod on realiseerimata.
// Kirjuta selle asemele oma lahenduse kood.

class Kodu9a {

    public static int pikimTee5Pikkus(int[][] a){
        return pikimTee5PikkusRek(a, 0, 0);
    }

    public static int pikimTee5PikkusRek(int[][] a, int i, int j){
        if (a[i][j] % 5 == 0){
            return Integer.MAX_VALUE;
        }

        if (i == a.length - 1 && j == a[i].length - 1){
            return a[i][j];
        }

        int iKaudu = Integer.MAX_VALUE;
        int jKaudu = Integer.MAX_VALUE;

        if (i < a.length - 1){
            iKaudu = pikimTee5PikkusRek(a, i+1, j);
        }

        if (j < a[i].length - 1){
            jKaudu = pikimTee5PikkusRek(a, i, j+1);
        }

        if (iKaudu != Integer.MAX_VALUE && jKaudu != Integer.MAX_VALUE){
            return a[i][j] + Math.max(iKaudu, jKaudu);
        } else if (iKaudu != Integer.MAX_VALUE){
            return a[i][j] + iKaudu;
        } else if (jKaudu != Integer.MAX_VALUE){
            return a[i][j] + jKaudu;
        }

        return Integer.MAX_VALUE;
    }

    public static int[] pikimTee5(int[][] a){
        throw new UnsupportedOperationException();
    }

    public static int[] pikiTee5Rek(int[][] a, int[] b){

    }

    public static void main(String[] args) {
        int[][] a = {{2, 2, 0, 2},
                {7, 5, 6, 5},
                {7, 0, 8, 1},
                {6, 5, 6, 9}};

        int[][] b = {{4, 4, 2, 2},
                {1, 5, 4, 6},
                {6, 6, 3, 1},
                {7, 7, 0, 6}};

        int[][] c = {{7},
                {8}};

        System.out.println(pikimTee5Pikkus(c));
    }

}
