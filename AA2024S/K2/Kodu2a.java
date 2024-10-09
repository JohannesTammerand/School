import java.util.Arrays;

public class Kodu2a {
    public static boolean valik(int[] a) {
        a = sorteeri(a);
        for (int i = 0; i < a.length; i++) {
            if (valikRek(a, 0, i, 1)){
                return true;
            }
        }
        return false;
    }

    public static boolean valikRek(int[] a, int m, int start, int n){
        if (start == a.length){
            return false;
        }
        if (n > a.length/2){
            return false;
        }
        if (m + a[start] == 10000){
            return true;
        } else if (m + a[start] > 10000){
            return false;
        }
        for (int i = start+1; i < a.length; i++) {
            if (valikRek(a, m+a[start], i, n+1)){
                return true;
            }
        }
        return false;
    }

    public static int[] sorteeri(int[] a){
        int n = a.length;
        for (int interval = n/2; interval > 0; interval /= 2){
            for (int i = interval; i < n; i++){
                int temp = a[i];
                int j;
                for (j = i; j >= interval && a[j - interval] <= temp; j -= interval) {
                    a[j] = a[j-interval];
                }
                a[j] = temp;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = {3341, 574, 2292, 1864, 2831, 4195, 3793, 639};
        System.out.println(valik(a));
    }
}