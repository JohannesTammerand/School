import java.util.Arrays;

/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2023/2024 kevadsemester
 *
 * Kontrolltöö nr 1
 * Teema: KT1
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class Kt1 {

    /**
     * Joonistab ekraanile kõrvuti n võrdhaarset kolmnurka, mille haarad moodustuvad n plussist
     * @param n n >= 2
     */
    public static void kolmnurgad(int n) {
        String valjund = "";
        for (int i = 0; i < 2*n-1; i++) {
            valjund = "";
            if (i == 0 || i == 2*n-2){
                for (int j = 0; j < n; j++) {
                    valjund += "+" + " ".repeat(n-1);
                }
            } else if (i < n){
                for (int j = 0; j < n; j++) {
                    valjund += "+" + " ".repeat(i-1) + "+" + " ".repeat(n-i-1);
                }
            } else {
                for (int j = 0; j < n; j++) {
                    valjund += "+" + " ".repeat(2*n-3 - i) + "+" + " ".repeat(i-n+1);
                }
            }
            System.out.println(valjund);
        }
    }

    /**
     * Leiab massiivist suurima osajärjendi pikkuse, mis on aritmeetiline jada
     * @param a Antud jada
     * @return Leitud pikima osajärjendi pikkus
     */
    public static int leiaAritmJada(int[] a) {
        int[][] jarjendid = new int[a.length][a.length];
        int[] pikkused = new int[a.length];

        for (int i = 0; i < jarjendid.length; i++) {
            jarjendid[i][0] = a[0];
        }

        for (int i = 0; i < pikkused.length; i++) {
            pikkused[i] = 1;
        }

        for (int nr : a){
            for (int i = 0; i < jarjendid.length; i++) {
                if (nr > jarjendid[i][pikkused[i]-1]){
                    jarjendid[i][pikkused[i]] = nr;
                    pikkused[i] += 1;
                    break;
                }
            }
        }

        int pikim = 0;
        for (int pikkus : pikkused){
            pikim = Math.max(pikim, pikkus);
        }

        return pikim;
    }

    /**
     * Leiab maatriksist suurima 3x3 alammaatriksi, mille kõik elemendid on erinevad
     * @param a Antud maatriks
     * @return  Leitud alammaatriksi ülemise vasaku elemendi koordinaat
     */
    public static int[] eriArvudega3Maatriks(int[][] a){
        for (int i = 0; i < a.length-2; i++) {
            for (int j = 0; j < a[i].length-2; j++) {
                int[] arvud = new int[9];
                boolean onSees = false;
                int index = 0;

                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 3; x++) {
                        arvud[index] = a[i+y][j+x];

                        for (int k = 0; k < index; k++) {
                            if (arvud[k] == a[i+y][j+x]){
                                onSees = true;
                                break;
                            }
                        }

                        index++;
                        if (onSees){break;}
                    }
                    if (onSees){break;}
                }
                if(!onSees) {
                    int[] tulemus = {i, j};
                    return tulemus;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Kontrolltöö nr 1. \t\t\t\t Programmi väljund");
        System.out.println("==============================================");
        System.out.println("Kolmnurgad suurusega 5:");
        kolmnurgad(5);
        System.out.println("\n");

        int[] b = {1, 4, 9, 2, 90, 4, 5, 7, 100, 101};
        System.out.println("Massiivi " + Arrays.toString(b) + " pikima aritemmtilise jada pikkus: ");
        System.out.println(leiaAritmJada(b));
        System.out.println("\n");

        int[][] a = {{1, 2, 3, 4},
                     {4, 7, 6, 5},
                     {15, 19, 9, 17},
                     {10, 15, 12, 3}};
        System.out.println("Maatriks:");
        for (int[] a1 : a){
            System.out.println(Arrays.toString(a1));
        }
        System.out.println("Selle suurima erinevate elementidega alammaatriksi kordinaat: ");
        System.out.println(Arrays.toString(eriArvudega3Maatriks(a)));

        System.out.println("==============================================");
    }

}
