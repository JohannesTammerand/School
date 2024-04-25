import java.util.Arrays;

/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2023/2024 kevadsemester
 *
 * Kodutöö nr 3b
 * Teema: Maatriksite ridades korduvate elementide leidmine
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class Kodu3b {

    /**
     * Maatriksite ridades korduvate elementide leidmine
     * @param a Antud täisarvude maatriks
     * @return Kasvavalt sorteeritud massiiv, mis sisaldab leitud korduvaid elemente
     */
    public static int[] korduvadRidades(int[][] a){
        int[] tulemus = a[0];
        boolean[] kuulub = new boolean[tulemus.length];
        for (int i = 0; i < kuulub.length; i++) {
            kuulub[i] = true;
        }

        for (int[] rida : a){
            boolean[] loetud = new boolean[rida.length];

            for (int i = 0; i < tulemus.length; i++){
                if (kuulub[i]) {
                    boolean leitud = false;
                    for (int j = 0; j < rida.length; j++) {
                        if (tulemus[i] == rida[j] && !loetud[j]) {
                            leitud = true;
                            loetud[j] = true;
                            break;
                        }
                    }
                    if (!leitud) {
                        kuulub[i] = false;
                    }
                }
            }
        }

        int kuuluvaid = 0;
        for (boolean b : kuulub){
            if (b){kuuluvaid++;}
        }

        int[] tulemus2 = tulemus;
        tulemus = new int[kuuluvaid];
        int j = 0;
        for (int i = 0; i < tulemus2.length; i++) {
            if (kuulub[i]){
                tulemus[j] = tulemus2[i];
                j++;
            }
        }

        return sorteeri(tulemus);
    }

    //Sorteerimise algoritmid võtsin enda esimese kodutöö lahendusest

    /**
     * Leiab massiivi väikseima elemendi indeksi, mis asub peale indeksit i
     * @param massiiv Antud täisarvude massiiv
     * @param i
     * @return Massiivi väikseima elemndi indeks
     */
    static int leiaVaikseim(int[] massiiv, int i){
        for (int j = i + 1; j < massiiv.length; j++) {
            if(massiiv[j] < massiiv[i]){
                i = j;
            }
        }
        return i;
    }

    /**
     * Sorteerib massiivi kasvavaks
     * @param massiiv Antud massiiv
     * @return Kasvavalt sorteeritud massiiv
     */
    static int[] sorteeri(int[] massiiv){
        int i;
        for (int k = 0; k < massiiv.length-1; k++){
            i = leiaVaikseim(massiiv, k);

            int vaikseim = massiiv[i];
            massiiv[i] = massiiv[k];
            massiiv[k] = vaikseim;
        }
        return massiiv;
    }

    public static void main(String[] args) {
        System.out.println("Kodutöö nr 3b. \t\t\t\t Programmi väljund");
        System.out.println("==============================================");

        System.out.println("\nn = 4");
        System.out.println("Algne massiiv:");
        int[][] a = {{1, 1, 2, 1},
                     {1, 2, 1, 1},
                     {2, 1, 1, 1},
                     {1, 5, 4, 1}};

        for (int[] a1 : a){
            String rida = "";
            for (int a2 : a1){
                rida += a2 + "  ";
            }
            System.out.println(rida);
        }

        System.out.println("Igas reas esinavate massiiv:");
        int[] tulemus = korduvadRidades(a);
        System.out.println(Arrays.toString(tulemus));


        System.out.println("\nn = 5");
        System.out.println("Algne massiiv:");
        int[][] b = {{7, 8, 3, 7, 4},
                     {4, 7, 7, 8, 6},
                     {8, 1, 6, 7, 7},
                     {7, 8, 3, 4, 2},
                     {8, 8, 5, 4, 7}};

        for (int[] b1 : b){
            String rida = "";
            for (int b2 : b1){
                rida += b2 + "  ";
            }
            System.out.println(rida);
        }

        System.out.println("Igas reas esinavate massiiv:");
        tulemus = korduvadRidades(b);
        System.out.println(Arrays.toString(tulemus));

        System.out.println("\nn = 6");
        System.out.println("Algne massiiv:");
        int[][] c = {{9, 9, 9, 9, 9, 9},
                     {9, 9, 9, 9, 9, 1},
                     {9, 9, 9, 9, 1, 1},
                     {9, 9, 9, 1, 1, 1},
                     {9, 9, 1, 1, 1, 1},
                     {9, 1, 1, 1, 1, 1}};

        for (int[] c1 : c){
            String rida = "";
            for (int c2 : c1){
                rida += c2 + "  ";
            }
            System.out.println(rida);
        }

        System.out.println("Igas reas esinavate massiiv:");
        tulemus = korduvadRidades(c);
        System.out.println(Arrays.toString(tulemus));

        System.out.println("==============================================");
    } // main
} // klass
