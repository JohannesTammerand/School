import com.sun.jdi.connect.AttachingConnector;

import java.util.Arrays;

/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2023/2024 kevadsemester
 *
 * Kodutöö nr 5
 * Teema: Maatriksi pööramine
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class Kodu5{

    /**
     * Pöörab maatriksi elemendid ringi k absoluutväärtuse korda. Kui k on positiivne, keerab päripäeva, vastasel juhul vastuoäeva.
     * @param a Pööratav maatriks
     * @param k
     * @return Pööratud maatriks
     */
    public static int[][] pööre(int[][] a, int k){
        int[][] tulemus = new int[a.length][a[0].length];

        for (int y = 0; y < a.length; y++) {
            for (int x = 0; x < a[y].length; x++) {
                int[] uusKoht = liiguta(a.length, a[y].length, new int[]{y, x}, k);

                tulemus[uusKoht[0]][uusKoht[1]] = a[y][x];
            }
        }

        return tulemus;
    }

    /**
     * Abifunktsioon Maatriksi elementide liigutamiseks. Võtab maatriksi kõrguse, pikkuse, liigutatava elemendi koordinaadid
     * ning peafunktsiooni k
     * @param korgus maatriksi kõrgus (y)
     * @param laius maatriksi laius (x)
     * @param koordinaadid Elemendi koordinaadid, mida liigutada kujul (y, x)
     * @param k
     * @return Elemendi uus positsioon kujul (y, x)
     */
    public static int[] liiguta(int korgus, int laius, int[] koordinaadid, int k){
        int x = koordinaadid[1];
        int y = koordinaadid[0];
        int kaugus = Math.min(Math.min(y, korgus - y - 1), Math.min(x, laius - x - 1)); //Elemendi kaugus kõige lähemast maatriksi äärest

        int kPos = k > 0 ? 1 : -1; // 1, kui k > 0, -1 muidu
        int kNeg = k > 0 ? -1 : 1; // -1, kui k > 0, 1 muidu

        int[] suund = {0, 0}; //Hoiab elemendi liikumissuunda kujul (y, x)
        if (y == kaugus){ //Annab elemendile liikumissuuna
            suund[1] = kPos;
        } else if (y == korgus-kaugus-1){
            suund[1] = kNeg;
        } else if (x == kaugus){
            suund[0] = kNeg;
        } else if (x == laius-kaugus-1) {
            suund[0] = kPos;
        }

        if (kaugus < Math.min(korgus, laius)/2){
            for (int i = 0; i < Math.abs(k); i++) {
                x += suund[1];
                y += suund[0];

                if (x < kaugus) {
                    suund[0] = kNeg;
                    suund[1] = 0;
                    x = kaugus;
                    y += suund[0];
                } else if (x > laius - kaugus - 1) {
                    suund[0] = kPos;
                    suund[1] = 0;
                    x = laius - kaugus - 1;
                    y += suund[0];
                } else if (y < kaugus) {
                    suund[1] = kPos;
                    suund[0] = 0;
                    y = kaugus;
                    x += suund[1];
                } else if (y > korgus - kaugus - 1) {
                    suund[1] = kNeg;
                    suund[0] = 0;
                    y = korgus - kaugus - 1;
                    x += suund[1];
                }
            }
        } else if (kaugus >= korgus/2){ //See osa ning järgnev on keskel oleva ribaga tegelemiseks
            for (int i = 0; i < Math.abs(k); i++) {
                suund[1] = k < 0 ? -1 : 1;
                x += suund[1];
                if (x < kaugus){
                    x = laius - kaugus-1;
                } else if (x > laius - kaugus -1) {
                    x = kaugus;
                }
            }
        } else {
            for (int i = 0; i < Math.abs(k); i++) {
                suund[0] = k < 0 ? -1 : 1;
                y += suund[0];
                if (y < kaugus){
                    y = korgus - kaugus - 1;
                } else if (y > korgus - kaugus - 1) {
                    y = kaugus;
                }
            }
        }

        return new int[]{y, x};
    }

    public static void main(String[] args) {
        System.out.println("Kodutöö nr 5. \t\t\t\t Programmi väljund");
        System.out.println("==============================================");

        int[][] a = {{0, 1, 2},
                     {3, 4, 5},
                     {6, 7, 8},
                     {9, 10, 11},
                     {12, 13, 14},
                     {15, 16, 17},
                     {18, 19, 20},
                     {21, 22, 23},
                     {24, 25, 26}};

        System.out.println("Antud maatriks:");
        for (int[] a1 : a){
            System.out.println(Arrays.toString(a1));
        }
        System.out.println();

        System.out.println("Maatriks pööratud -3 kohta:");
        int[][] a1 = pööre(a, -3);
        for (int[] a2 : a1){
            System.out.println(Arrays.toString(a2));
        }
        System.out.println("\n----------------------------------\n");

        int[][] b = {{1, 2, 3, 4, 5},
                     {6, 7 ,8, 9, 10},
                     {11, 12, 13, 14, 15},
                     {16, 17, 18, 19, 20},
                     {21, 22, 23, 24, 25},
                     {26, 27, 28, 29, 30},
                     {31, 32, 33, 34, 35}};

        System.out.println("Antud maatriks:");
        for (int[] b1 : b){
            System.out.println(Arrays.toString(b1));
        }
        System.out.println();

        System.out.println("Maatriks pööratud 3 kohta:");
        int[][] b1 = pööre(b, 3);
        for (int[] b2 : b1){
            System.out.println(Arrays.toString(b2));
        }
        System.out.println();

        System.out.println("==============================================");
    }//main

}//klass