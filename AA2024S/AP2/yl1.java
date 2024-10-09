package AP3;

import java.util.ArrayList;
import java.util.Arrays;

public class yl1 {
    public static void main(String[] args) {
        System.out.println(yl3(5, 1, ""));
    }

    public static void yl1(String s, int n, int k, int h){
        if (n == 0){
            System.out.println(s);
        } else {
            if (k > 0){
                yl1(s + "0", n-1, k-1, h);}
            if (h > 0){
                yl1(s + "1", n-1, k, h-1);}
            if (n > k+h){
                yl1(s + "2", n-1, k, h);}
        }
    }

    public static int yl2(int n, String s){
        if (n == 0){
            System.out.println(Arrays.toString(s.split("")));
            return 1;
        } else if (n == 1){
            s += 1;
            System.out.println(Arrays.toString(s.split("")));
            return 1;
        }else {
            int n2 = 0;
            n2 += yl2(n-1, s+"1");
            if (n >= 2){
                n2 += yl2(n-2, s+"2");
            }
            return n2;
        }
    }

    public static int yl2L(int n, String s){
        if (n == 0){
            System.out.println(Arrays.toString(s.split("")));
            return 1;
        } else {
            int n2 = 0;
            if (s.length() >= 2){
                if (!(s.charAt(s.length()-2) == '1' && s.charAt(s.length()-1) == '2')){
                    n2 += yl2L(n-1, s+"1");
                }
                if (n >= 2 && !(s.charAt(s.length()-2) == '2' && s.charAt(s.length()-1) == '1')){
                    n2 += yl2L(n-2, s+"2");
                }
            } else {
                n2 += yl2L(n-1, s+"1");
                if (n >= 2){
                    n2 += yl2L(n-2, s+"2");
                }
            }
            return n2;
        }
    }

    public static int yl3(int n, int i, String s){
        if (n == 0){
            System.out.println(Arrays.toString(s.split("")) + i);
            return 1;
        } else {
            int n2 = 0;
            n2 = yl3(n-1, i, s+"1");
            i+=n2;
            if (n >= 2){
                n2 += yl3(n-2, i, s+"2");
            }
            return n2;
        }
    }
}
