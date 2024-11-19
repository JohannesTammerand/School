import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class AP3 {
    public static void main(String[] args) {
        yl3(4);
    }

    public static boolean yl1(String s){
        Deque<Character> magasin = new ArrayDeque<>();
        int i = 0;
        for (; i < s.length()/2; i++) {
            magasin.add(s.charAt(i));
        }

        if (s.length() % 2 != 0){i++;}

        for (; i < s.length(); i++){
            if (s.charAt(i) != magasin.removeLast()){
                return false;
            }
        }
        return true;
    }

    public static int yl2a(int n){
        if (n==0) return 0;
        if (n==1) return 1;
        Deque<Integer> fibo = new ArrayDeque<>(List.of(0, 1));
        for (int i = 2; i <= n; i++) {
            fibo.add(fibo.getLast() + fibo.pop());
        }
        return fibo.removeLast();
    }

    public static int yl2b(int n){
        if (n==0) return 0;
        if (n==1) return 1;
        Queue<Integer> fibo = new ArrayDeque<>(List.of(1, 0));
        for (int i = 2; i <= n; i++){
            int a = fibo.remove();
            fibo.add(a + fibo.remove());
            fibo.add(a);
        }
        return fibo.element();
    }

    public static void yl3(int n){
        Deque<Integer> bitid = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            bitid.add(0);
        }
        System.out.println(bitid.toString());
        for (int oiged = 1; oiged <= n; oiged++) {
            for (int i = 0; i < oiged; i++) {
                bitid.removeLast();
            }
            bitid.add(1);
            for (int i = 1; i < oiged; i++) {
                bitid.add(0);
            }
            //System.out.println(bitid.toString());
            for (int i = 0; i <= oiged; i++) {
                for (int j = 0; j < i; j++) {
                    for (int k = 0; k < j; k++) {
                        bitid.removeLast();
                    }
                    bitid.add(1);
                    for (int k = 1; k < j; k++) {
                        bitid.add(0);
                    }
                    System.out.println(bitid.toString());
                }
            }
        }
    }
}
