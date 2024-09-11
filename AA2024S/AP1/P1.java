public class P1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long stop = System.currentTimeMillis();
        long n = 0;
        for(int i = 0; stop - start < 1000; i++){
            start = System.currentTimeMillis();

            n = fibo2(i);

            stop = System.currentTimeMillis();
            System.out.println(n);
        }
        System.out.println(n);
    }

    public static int fibo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibo(n - 1) + fibo(n - 2);
    }

    public static long fibo2(int n){
        if (n == 0) return 0;
        if (n == 1) return 1;
        long n1 = 0;
        long n2 = 1;
        long n3 = 1;
        for (int i = 0; i < n-1; i++){
            n3 = n2 + n1;
            n1 = n2;
            n2 = n3;
        }
        return n3;
    }
}
