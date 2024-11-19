import java.util.Arrays;

/***********************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2024/2025 kevadsemester
 *
 * Kodutöö nr 2a
 * Teema: Rekursioon. Variantide läbivaatamine
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class Kodu2a {
    /**
     * Meetodi ülesandeks on kontrollida, kas antud järjendis leidub mingi arvude valik, mille summa on 10000
     * Valitud arvude kogus ei tohi olla kõrgem kui pool järjendi suurusest
     * Lahendamiseks kasutab rekursiivset abifunktsiooni
     * @param a Antud järjend
     * @return Tõeväärtus
     */
    public static boolean valik(int[] a) {
        a = sorteeri(a); //Abifunktsioon eeldab, et antud järjend on sorteeritud
        for (int i = 0; i < a.length; i++) {
            if (valikRek(a, 0, i, 1)){
                return true;
            }
        }
        return false;
    }

    /**
     * Rekursiivne abifunktsioon funktsioonile valik.
     * Eeldab, et antud järjend on sorteeritud mittekasvavasse järjekorda
     * @param a Antud järjend
     * @param m Seni valitud arvude summa
     * @param start Stardiindeks, kust funktsioon järjendis erve valib
     * @param n Valitud arvude kogus
     * @return Tõeväärtus
     */
    public static boolean valikRek(int[] a, int m, int start, int n){
        if (start == a.length){
            return false;
        }
        if (n > a.length/2){ //Valitud arvude kogus ei tohi olla kõrgem kui pool järjendi suurusest
            return false;
        }
        if (m + a[start] == 10000){
            return true;
        } else if (m + a[start] > 10000){
            return false;
        }
        for (int i = start+1; i < a.length; i++) {
            if (valikRek(a, m+a[start], i, n+1)){
                return true;
            }
        }
        return false;
    }

    /**
     * Mullimeetodit kasutav sorteerimimeetod. Võetud enda esimese kodutöö lahendusest.
     * @param a Sorteerimata järjend
     * @return Mittekasvavalt sorteeritud järjend
     */
    public static int[] sorteeri(int[] a){
        int n = a.length;
        for (int interval = n/2; interval > 0; interval /= 2){
            for (int i = interval; i < n; i++){
                int temp = a[i];
                int j;
                for (j = i; j >= interval && a[j - interval] <= temp; j -= interval) {
                    a[j] = a[j-interval];
                }
                a[j] = temp;
            }
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = {3341, 574, 2292, 1864, 2831, 4195, 3793, 639};
        System.out.println(valik(a));
    }
}