import java.util.Arrays;

/**
 * Õpilase nimi: Johannes Tammerand
 * Programmeerimine 2 - Eksamiosa 1 05.06.2024.
 * UnsupportedOperationException tähistab, et meetod on realiseerimata. Palume mitte "package" märksõna lisada, mallis
 * olevate meetodite signatuure muuta, ega (ka realiseerimata) meetodeid kustutada!
 */
public class E1 {

    /**
     * Meetodi ülesandeks on kujutada ekraanile antud naturaalarvu n suuruses poolkuu
     * @param n Antud naturaalarv, n >= 2
     */
    public static void poolkuu(int n) {
        String valjund = "";
        for (int i = n; i > 0; i--) { //Väljastab esimese poole ridadest + keskmise
            valjund = "";
            valjund += " ".repeat(i*(i-1)/2);
            valjund += "+".repeat(i);
            System.out.println(valjund);
        }

        for (int i = 2; i <= n; i++) { //Väljastab teise poole ridadest
            valjund = "";
            valjund += " ".repeat(i*(i-1)/2);
            valjund += "+".repeat(i);
            System.out.println(valjund);
        }
    }

    /**
     * Väljastab vähima indeksi, millest eespool ja tagapool asuvate antud massiivi elementide summad on võrdsed
     * @param a Antud täisarvude massiiv
     * @return Leitud indeks
     */
    public static int võrdneEesTaga(int[] a) {
        if (a.length == 0){
            return 0;
        }

        for(int i = 0; i < a.length; i++){
            int summaEes = 0;
            int summaTaga = 0;

            for (int j = 0; j < a.length; j++) {
                if (j < i){
                    summaEes += a[j];
                } else {
                    summaTaga += a[j];
                }
            }

            if (summaTaga == summaEes){
                return i;
            }
        }

        return -1;
    }

    /**
     * Meetodi ülesandeks on antud maatriksist leida elemendid, mis võrduvad antud muutuja x-iga, ning siis muuta
     * selle elemendi asukohast kõikide lähtuvate diagonaalide elemendid 0-ks
     * @param a Antud maatriks
     * @param x Muutuja, millega maatriksi elemente võrrelda
     * @return Uus maatriks
     */
    public static int[][] kustutaDiag(int[][] a, int x) {
        int h = a.length;
        int l = a[0].length;

        int[][] tulemus = new int[h][l];
        for (int i = 0; i < h; i++) { //Kopeerib maatriksi a maatrikisisse tulemused, et need üksteist pärastpoole ei mõjutaks
            for (int j = 0; j < l; j++) {
                tulemus[i][j] = a[i][j];
            }
        }


        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                if (a[i][j] == x){
                    tulemus[i][j] = 0;

                    int x1 = j+1; //Diagonaalide muutmine käib kahes osas: üles ja alla, milleks on kaks erinevat tsüklit
                    int x2 = j-1; //Mõlemas osas on veel eraldi diagonaalide muutmine vasakule ja paremale
                    for (int y = i+1; y < h; y++) {
                        if (x1 < l){
                            tulemus[y][x1] = 0;
                            x1++;
                        }

                        if (x2 >= 0){
                            tulemus[y][x2] = 0;
                            x2--;
                        }
                    }

                    x1 = j+1;
                    x2 = j-1;
                    for (int y = i-1; y >= 0; y--) {
                        if (x1 < l){
                            tulemus[y][x1] = 0;
                            x1++;
                        }

                        if (x2 >= 0){
                            tulemus[y][x2] = 0;
                            x2--;
                        }
                    }
                }
            }
        }

        return tulemus;
    }
}