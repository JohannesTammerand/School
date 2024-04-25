import java.util.ArrayList;
import java.util.Arrays;

/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2023/2024 kevadsemester
 *
 * Kodutöö nr 7a
 * Teema: Rekursioon
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

class Kodu7a {

    /**
     * Funktsiooni ülesandeks on leida antud naturaalarvude järjendist kõik võimalikud aritmeetilised tehtud, mille tulemus jääb antud vahemikku
     * @param a Naturaalarvude järjend
     * @param x Vahemiku alampiir
     * @param y Vahemiku ülempiir
     * @return Sõnede järjend
     */
	public static String[] avaldisedLõigus1(int[] a, int x, int y){
        if (a.length == 0){ return new String[0];} // Tühja järjendi puhul tagastab tühja järjendi

        ArrayList<String> tulemus = new ArrayList<String>();
        tulemus = avaldisedLõigus1Abi(eemaldaEsimene(a), x, y, a[0], String.valueOf(a[0])); //Rekursiivne abifunktsioon

        String[] arr = new String[tulemus.size()];
        arr = tulemus.toArray(arr);

        arr = sorteeri(arr, 0);

        return arr;
	}

    /**
     * Rekursiivne abifunktsioon ülesande lahendamiseks, tööpõhimõte on sama, mis avaldisedLõigus1 funktsioonil
     * @param a Antud naturaalarvude järjend
     * @param x Vahemiku alampiir
     * @param y Vahemiku ülempiir
     * @param n Senise aritmeetilise tehte tulemus
     * @param sone Senine aritmeetiline tehe
     * @return Sõnede ArrayList, mis sisaldab kõiki vahemikku jäävaid aritmeetilisi tehteid
     */
    public static ArrayList<String> avaldisedLõigus1Abi(int[] a, int x, int y, int n, String sone){
        ArrayList<String> tulemus = new ArrayList<String>(); //ArrayList, mis sisaldab kõiki saadud aritmeetilisi tehteid
        if (a.length == 0){
            tulemus.add(sone + "=" + String.valueOf(n)); // Kui a on tühi järjend, lisab tulemusse saadud sõne koos tehte vastusega
        } else {
            if (n + a[0] <= y){ //Kontrollib, kas antud vahemikus saab veel liitmistehet teha
                tulemus.addAll(avaldisedLõigus1Abi(eemaldaEsimene(a), x, y, n + a[0], sone + "+" + String.valueOf(a[0])));
            }
            if (n - a[0] >= x){ //Kontrollib, kas antud vahemikus saab veel lahutamistehet teha
                tulemus.addAll(avaldisedLõigus1Abi(eemaldaEsimene(a), x, y, n - a[0], sone + "-" + String.valueOf(a[0])));
            }
        }

        return tulemus;
    }

    /**
     * Eemaldab antud täisarvude järjendist esimese elemendi
     * @param a Järjend
     * @return Järjend ilma esimese elemendita
     */
    public static int[] eemaldaEsimene(int[] a){
        int[] tulemus = new int[a.length - 1];

        for (int i = 1; i < a.length; i++) {
            tulemus[i-1] = a[i];
        }

        return tulemus;
    }

    /**
     * Sorteerib avaldisedLõigus1 funktsioonist saadud järjendi rekursiivselt.
     * Lahendus võetud 8. loengust
     * @param a Järjend
     * @param n Sorteerimiseks kasutatav indeks
     * @return Sorteeritud järjend
     */
    public static String[] sorteeri(String[] a, int n){
        if (n == a.length - 1){
            return a;
        }

        int vahimaIndeks = 0;
        int vahim = 0;

        for (int i = n; i < a.length; i++){
            String nr = (a[i].substring(a[i].indexOf('=') + 1, a[i].length()));

            if (i == n){
                vahim = Integer.parseInt(nr);
                vahimaIndeks = n;
            } else if (Integer.parseInt(nr) < vahim){
                vahim = Integer.parseInt(nr);
                vahimaIndeks = i;
            }
        }

        String temp = a[n];
        a[n] = a[vahimaIndeks];
        a[vahimaIndeks] = temp;

        return sorteeri(a, n+1);
    }

    public static void main(String[] args) {
        System.out.println("Kodutöö nr 7a. \t\t\t\t Programmi väljund");
        System.out.println("==============================================");
        int[] a={2, 12, 5, 2};
        int x1=-15;
        int y1=15;

        System.out.println("Antud järjend: " + Arrays.toString(a));
        System.out.println("Vahemik: [" + x1 + "; " + y1 + "]");
        System.out.println("Saadud avaldised:");
        String[] a1 = avaldisedLõigus1(a, x1, y1);
        for (String a2 : a1){
            System.out.println(a2);
        }
        System.out.println("\n");


        int[] b={3, 7, 6, 9};
        int x2=-30;
        int y2=30;

        System.out.println("Antud järjend: " + Arrays.toString(b));
        System.out.println("Vahemik: [" + x2 + "; " + y2 + "]");
        System.out.println("Saadud avaldised:");
        String[] b1 = avaldisedLõigus1(b, x2, y2);
        for (String b2 : b1){
            System.out.println(b2);
        }

        System.out.println("==============================================");
    }//peameetod


}//Kodu7a
