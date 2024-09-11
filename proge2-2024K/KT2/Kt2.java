import java.util.Arrays;

/**
 * Õpilase nimi: Johannes Tammerand
 * Programmeerimine 2 - KT 2 lisakatse 22.05.2024.
 * UnsupportedOperationException tähistab, et meetod on realiseerimata. Kirjuta selle asemele oma lahenduse kood.
 * Palume mitte "package" märksõna lisada, mallis olevate meetodite signatuure muuta, ega (ka realiseerimata) meetodeid kustutada!
 */
public class Kt2 {

    /**
     * Meetodi ülesandeks on tagastada vähim Fibonacci arv, mis on antud naturaalarvust suurem
     * Kasutab töö tegemiseks rekursiivset abimeetodit fiboXRek
     * @param n Antud naturaalarv
     * @return Väikseim antud arvust suurem Fibonacci arv
     */
    public static int fiboX(int n) {
        return fiboXRek(n, 0, 1);
    }

    /**
     * Rekursiivne abimeetod fiboX meetodile, kus leitakse nõutud arv
     * @param n Antud naturaalarv
     * @param eelmine1 praegusest Fibonacci arvust üleelmine arv
     * @param eelmine2 praegusest Fibonacci arvust eelmine arv
     * @return Nõutud Fibonacci arv
     */
    public static int fiboXRek(int n, int eelmine1, int eelmine2){
        if (eelmine1 + eelmine2 > n){
            return eelmine1 + eelmine2;
        }

        return fiboXRek(n, eelmine2, eelmine1 + eelmine2);
    }

    /**
     * Meetodi ülesandeks on väljastada antud massiivist alguses elemendid paarisarvulise indeksi kasvavas järjestuses
     * ning peale seda massiivi elemendid paarituarvulise indeksi kahanevas järjestuses
     * Ülesannet teostatakse rekursiivse abimeetodiga üleÜheRek
     * @param a Antud massiiv
     */
    public static void üleÜhe(int[] a) {
        üleÜheRek(a, 0, true);
    }

    /**
     * Rekursiivne abimeetod üleÜhe meetodile
     * Kuna massiivi läbimine hakkab alati indeksilt 0, pole indeksi paarisarvulisust vaja kontrollida
     * @param a Antud massiiv
     * @param n Praegune indeks
     * @param kasvav Kas massiivi läbitakse hetkel kasvavas või kahanevas järjestuses
     */
    public static void üleÜheRek(int[] a, int n, boolean kasvav){
        System.out.println(a[n]);

        if (kasvav){
            if (n == a.length - 1){ //Kui indeks on kas viimane või eelviimane, läheb üle kahanemisele
                üleÜheRek(a, n-1, false); // Kui viimane indeks on paarisarvuline, hakkab kahanemine eelviimaselt indeksilt
            } else if (n == a.length - 2){
                üleÜheRek(a, n+1, false); // Kui eelviimane indeks on paarisarvuline, hakkab kahanemine viimaselt indeksilt
            } else {
                üleÜheRek(a, n+2, true);
            }
        } else {
            if (n >= 2){ // Vaatab, kas massiivis saab veel alguse poole minna
                üleÜheRek(a, n-2, false);
            }
        }
    }

    /**
     * Meetodi ülesandeks on esitada antud arv kõigi võimalike arvude 3 ja 4 summadena, kus liidetavate järjekord on oluline
     * Tinfimuseks on veel, et kumbki arv peab summas esinema vähemalt ühe korra ning neljasi ei tohi olla rohkem kui kolmesi
     * Ülesannet teostatakse rekursiivse abimeetodiga summad34Rek
     * @param n Antud arv
     */
    public static void summad34(int n) {
        summad34Rek(n, 1, 0, "3");
        summad34Rek(n, 0, 1, "4");
    }

    /**
     * Rekursiivne abimeetod summad34 meetodile
     * @param n Antud arv
     * @param kolmHulk Kui palju kolmesid hetkeses summas on
     * @param neliHulk Kui palju neljasid hetkeses summas on
     * @param valjund Seni tehtud summa sõnena
     */
    public static void summad34Rek(int n, int kolmHulk, int neliHulk, String valjund){
        if (kolmHulk * 3 + neliHulk * 4 == n && neliHulk <= kolmHulk && (kolmHulk >= 1 && neliHulk >= 1)){
            System.out.println(valjund);
        } else if (kolmHulk * 3 + neliHulk * 4 < n){ // Kui summa on arvust suurem, pole mõtet rekursiooni edasi teha
            summad34Rek(n, kolmHulk+1, neliHulk, valjund+"+3");
            summad34Rek(n, kolmHulk, neliHulk+1, valjund+"+4");
        }
    }

    public static void main(String[] args) {
        System.out.println("Järelkontrolltöö nr 2. \t\t\t\t Programmi väljund");
        System.out.println("==============================================");
        int n = 175;
        System.out.println("Arvust " + n + "Väikseim suurem Fibonacci arv on " + fiboX(n));
        System.out.println();

        int[] a = {0, 1, 2, 3, 4, 5};
        System.out.println("Antud järjend: " + Arrays.toString(a));
        System.out.println("Üle ühe elemendid: ");
        üleÜhe(a);
        System.out.println();

        n = 25;
        System.out.println("Arvu " + n + " kõik viisid esitada arvude 3 ja 4 summana:");
        summad34(n);

        System.out.println("==============================================");

    }

}
