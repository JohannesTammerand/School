package K7;

import com.sun.security.jgss.GSSUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Kodu7b {
    public static class Tee {
        int start;
        int end;
        int kaugus;

        public Tee(int start, int end, int kaugus){
            this.start = start;
            this.end = end;
            this.kaugus = kaugus;
        }
    }

    public static void kaugused(String lähtelinn, int x, String[] linnad, int[][] M) {
        int linnaNr = 0;
        for (int i = 0; i < linnad.length; i++) {
            if (Objects.equals(linnad[i], lähtelinn)){
                linnaNr = i;
            }
        }

        ArrayList<Tee> teekonnad = new ArrayList<>();
        int[] kaugusedAlgusest = new int[linnad.length];
        teekonnad.add(new Tee(linnaNr, linnaNr, x));

        while (!teekonnad.isEmpty()){
            Tee tee = teekonnad.get(0);
            eemaldaKuhjast(teekonnad, tee);
            linnaNr = tee.end;

            for (int i = 0; i < linnad.length; i++) {
                if (M[linnaNr][i] > 0 && M[linnaNr][i] <= x){
                    lisaKuhja(teekonnad, new Tee(linnaNr, i, M[linnaNr][i]));
                    kaugusedAlgusest[i] = kaugusedAlgusest[linnaNr] + tee.kaugus;
                    for (int j = 0; j < linnad.length; j++) {
                        M[j][i] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < kaugusedAlgusest.length; i++){
            lisaKuhja(teekonnad, new Tee(0, i, kaugusedAlgusest[i]));
        }

        for (Tee t : teekonnad){
            System.out.println(linnad[t.end] + " " + t.kaugus);
        }
    }

    public static ArrayList<Tee> lisaKuhja(ArrayList<Tee> kuhi, Tee t){ kuhi.add(t);
        int idx = kuhi.size()-1;
        while (idx > 0 && kuhi.get((idx-1)/2).kaugus > kuhi.get(idx).kaugus){
            Tee temp = kuhi.get(idx);
            kuhi.set(idx, kuhi.get((idx-1)/2));
            kuhi.set((idx-1)/2, temp);
            idx = (idx-1)/2;
        }
        return kuhi;
    }

    public static ArrayList<Tee> eemaldaKuhjast(ArrayList<Tee> kuhi, Tee t) {
        //int idx = -1;
        for (int i = 0; i < kuhi.size(); i++) {
            if (kuhi.get(i) == t) {
                kuhi.set(i, kuhi.get(kuhi.size() - 1));
                //idx = i;
                break;
            }
        }
        return kuhi;
    }

    public static void main(String[] args) {
        int[][] M = new int[][]{{0, 12, 2147483647, 2147483647},
                            {12, 0, 2147483647, 2147483647},
                            {2147483647, 2147483647, 0, 2147483647},
                            {2147483647, 2147483647, 2147483647, 0}};
        String[] linnad = {"a", "b", "c", "d"};
        kaugused("a", 2147483646, linnad, M);
    }
}