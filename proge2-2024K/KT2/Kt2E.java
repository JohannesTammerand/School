import java.util.ArrayList;
import java.util.Arrays;

/**
 * Õpilase nimi: Johannes Tammerand
 * Programmeerimine 2 - KT 2 24.04.2024.
 * UnsupportedOperationException tähistab, et meetod on realiseerimata. Kirjuta selle asemele oma lahenduse kood.
 * Palume mitte "package" märksõna lisada, mallis olevate meetodite signatuure muuta, ega (ka realiseerimata) meetodeid kustutada!
 */
public class Kt2E {

    /**
     * Meetodi ülesandeks on asendada täisarvude järjendis iga mitteviimane arv selle järgnevate arvude maskimumiga
     * Ülesanne teostatakse kahe rekusriivse abimeetodi abil
     * @param a Antud järjend
     */
    public static void asendaMax(int[] a) {
        a = asendaMaxAbi1(a, 0);

        System.out.println(Arrays.toString(a));
    }

    /**
     * Rekursiivne abimeetod asendaMax meetodile, mis töötab tsüklina
     * @param a Antud järjend
     * @param n Rekursiooni sügavus e. järjendi indeks
     * @return Saadud järjend
     */
    public static int[] asendaMaxAbi1(int[] a, int n){
        if (n == a.length - 1){
            return a;
        }

        a[n] = asendaMaxAbi2(a, n+1);
        return asendaMaxAbi1(a, n+1);
    }

    /**
     * Abimeetod asendaMax meetodile, mis muudab järjendit nõuetekohaselt ja rekursiivselt
     * @param a Antud järjend
     * @param n Rekursiooni sügavus
     * @return Muudetud järjend
     */
    public static int asendaMaxAbi2(int[] a, int n){
        if (n == a.length - 1){
            return a[n];
        }

        int jargmine = asendaMaxAbi2( a, n+1);
        if (jargmine > a[n]){
            return jargmine;
        } else{
            return a[n];
        }
    }

    public static void väljastaOsajärjendid(int[] a) {
        väljastaOsajärjendidRek(a, 0, "");
    }

    public static void väljastaOsajärjendidRek(int[] a, int start, String output) {
        if (!output.equals("")) {
            System.out.println(output.trim());
        }
        for (int i = start; i < a.length; i++) {
            väljastaOsajärjendidRek(a, i + 2, output + " " + a[i]);
        }
    }

    /**
     * Meetodi ülesandeks on vaadata, kas kahel täisarvude järjendil leidub võrdse summaga eesosa
     * Ülesanne teostatakse rekursiivse abimeetodi abil
     * @param a Antud järjend
     * @param b Antud järjend
     * @return Tõeväärtus, kas leiduvad võrdsed eesosad
     */
    public static boolean kasPeavõrdsed(int[] a, int[] b) {
        return kasPeaVõrdsedRek(a, 1, b, 1, a[0], b[0]);
    }

    /**
     * Rekursiivne abimeetod kasPeavõrdsed meetodile
     * @param a Antud järjend
     * @param n Järjendi a indeks
     * @param b Antud järjend
     * @param m Järjendi b indeks
     * @param aSumma Järjendi a senise eesosa summa
     * @param bSumma Järjendi b senise eesosa summa
     * @return Tõeväärtus, kas leidub kaks võrdset eesosa
     */
    public static boolean kasPeaVõrdsedRek(int[] a, int n, int[] b, int m, int aSumma, int bSumma){
        if (aSumma == bSumma){
            return true;
        } else if (n == a.length - 1 && m == b.length){
            return aSumma + a[n] == bSumma;
        } else if (m == b.length){
            return kasPeaVõrdsedRek(a, n+1, b, 1, aSumma + a[n], b[0]);
        }

        return kasPeaVõrdsedRek(a, n, b, m+1, aSumma, bSumma + b[m]);
    }

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4, 5};
        väljastaOsajärjendid(a);


        /*
        System.out.println("Kontrolltöö nr 2. \t\t\t\t Programmi väljund");
        System.out.println("==============================================");
        System.out.println("Esimene meetod: asendaMax");
        int[] a = {3, 7, 4, 5, 2};
        System.out.println("Antud järjend: " + Arrays.toString(a));
        System.out.print("Muudetud järjend: ");
        asendaMax(a);
        System.out.println("\n");

        System.out.println("Teine meetod: kasPeavõrdsed");
        int[] b = {1, 2, 4, 3};
        int[] c = {9, 3, -2};
        System.out.println("Antud järjendid:");
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));

        if (kasPeavõrdsed(b, c)){
            System.out.println("On peavõrdsed");
        } else {
            System.out.println("Ei ole peavõrdsed");
        }

        System.out.println("==============================================");

         */

    }

}
