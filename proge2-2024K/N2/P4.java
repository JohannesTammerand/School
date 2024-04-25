package N2;

import java.util.Arrays;

public class P4 {
    public static void yl4_1(int n){
        char[][] a = new char[n][2*n-1];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = ' ';
            }
        }
        for (int i = 0; i < a.length; i++) {
            if (i == 0){
                for (int j = 0; j < n; j++) {
                    a[i][j] = '#';
                }
            } else if (i == a.length-1) {
                for (int j = 0; j < n ; j++) {
                    a[i][a[i].length-n+j] = '#';
                }
            } else {
                a[i][i] = '#';
                a[i][a[i].length+i-n] = '#';
            }
        }

        for (char[] a1 : a){
            System.out.println(Arrays.toString(a1));
        }

        char[][] b = new char[2*n-1][n];
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                b[i][j] = ' ';
            }
        }
    }

    public static void main(String[] args) {
        yl4_1(5);
    }
}
