import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/***********************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2024/2025 kevadsemester
 *
 * Kodutöö nr 4
 * Teema: Paisktabelid
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class Kodu4 {

    /**
     * Genereerib isikukoodi lähtudes reeglitest püstitatud <a href=https://et.wikipedia.org/wiki/Isikukood>siin.</a>
     * <br>
     * Numbrite tähendused:
     * <ul style="list-style-type:none">
     *      <li> 1 - sugu ja sünniaasta esimesed kaks numbrit, (1...8) </li>
     *      <li> 2-3 - sünniaasta 3. ja 4. numbrid, (00...99) </li>
     *      <li> 4-5 - sünnikuu, (01...12) </li>
     *      <li> 6-7 - sünnikuupäev (01...31) </li>
     *      <li> 8-10 - järjekorranumber samal päeval sündinute eristamiseks (000...999) </li>
     *      <li> 11 - kontrollnumber (0...9) </li>
     * </ul>
     *
     * @return Eesti isikukoodi reeglitele vastav isikukood
     */
    static long genereeriIsikukood() {
        ThreadLocalRandom juhus = ThreadLocalRandom.current();
        LocalDate sünnikuupäev = LocalDate.ofEpochDay(juhus.nextLong(-62091, 84006)); // Suvaline kuupäeva a 1800-2199
        long kood = ((sünnikuupäev.getYear() - 1700) / 100 * 2 - juhus.nextInt(2)) * (long) Math.pow(10, 9) +
                sünnikuupäev.getYear() % 100 * (long) Math.pow(10, 7) +
                sünnikuupäev.getMonthValue() * (long) Math.pow(10, 5) +
                sünnikuupäev.getDayOfMonth() * (long) Math.pow(10, 3) +
                juhus.nextInt(1000);
        int korrutisteSumma = 0;
        int[] iAstmeKaalud = {1, 2, 3, 4, 5, 6, 7, 8, 9, 1};
        for (int i = 0; i < 10; i++) {
            korrutisteSumma += (int) (kood / (long) Math.pow(10, i) % 10 * iAstmeKaalud[9 - i]);
        }
        int kontroll = korrutisteSumma % 11;
        if (kontroll == 10) {
            int[] iiAstmeKaalud = {3, 4, 5, 6, 7, 8, 9, 1, 2, 3};
            korrutisteSumma = 0;
            for (int i = 0; i < 10; i++) {
                korrutisteSumma += (int) (kood / (long) Math.pow(10, i) % 10 * iiAstmeKaalud[9 - i]);
            }
            kontroll = korrutisteSumma % 11;
            kontroll = kontroll < 10 ? kontroll : 0;
        }
        return kood * 10 + kontroll;
    }

    /**
     * Sorteerib isikukoodid sünniaja järgi:
     * <ul style="list-style-type:none">
     *     <li> a) järjestuse aluseks on sünniaeg, vanemad inimesed on eespool; </li>
     *     <li> b) kui sünniajad on võrdsed, määrab järjestuse isikukoodi järjekorranumber (kohad 8-10); </li>
     *     <li> c) kui ka järjekorranumber on võrdne, siis määrab järjestuse esimene number. </li>
     * </ul>
     *
     * @param isikukoodid sorteeritav isikukoodide massiiv
     */
    public static void sort(long[] isikukoodid) {
        List<Long>[] kuhjad = new ArrayList[8]; //Kuhjade hulk, kus kuhjas indeksiga n on kõik antud isikukoodid, mille esimene number on n+1
        for (int i = 0; i < kuhjad.length; i++) {
            kuhjad[i] = new ArrayList<>();
        }
        for (long id : isikukoodid){
            int esimene = esimeneNumber(id);
            kuhjad[esimene-1].add(id);
        }

        for (List<Long> l : kuhjad){ //Sorteerib kõik kuhjad
            positsioonimeetod(l);
        }

        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i = 1; i < 9; i+=2) { //Kombineerib kõik kuhjad ühte järjendisse, arvestades korraga kahte kuhja
            List<Long> esimene = kuhjad[i-1];
            List<Long> teine = kuhjad[i];
            while (i1 < esimene.size() || i2 < teine.size()){
                if (i1 >= esimene.size()){
                    isikukoodid[i3] = teine.get(i2);
                    i2++;
                    i3++;
                    continue;
                }
                if (i2 >= teine.size()){
                    isikukoodid[i3] = esimene.get(i1);
                    i1++;
                    i3++;
                    continue;
                }

                if (keskmine(esimene.get(i1)) <= keskmine(teine.get(i2))){
                    isikukoodid[i3] = esimene.get(i1);
                    i1++;
                    i3++;
                } else {
                    isikukoodid[i3] = teine.get(i2);
                    i2++;
                    i3++;
                }
            }
            i1 = 0;
            i2 = 0;
        }
    }

    /**
     * Sorteerib järjendi positsioonimeetodit ning loendamismeetodit kasutades
     * @param isikukoodid Antud järjend
     */
    public static void positsioonimeetod(List<Long> isikukoodid){
        if (isikukoodid.size() <= 1){
            return;
        }

        long[] tulemus = new long[isikukoodid.size()];
        long[] temp = new long[isikukoodid.size()];
        for (int i = 0; i < isikukoodid.size(); i++){
            temp[i] = isikukoodid.get(i);
        }

        for (int i = 1; i < 11; i++) { //Sorteerib positsioonimeetodiga, arvestades kõike peale esimese numbri
            long baas = 10;
            for (int j = 1; j < i; j++) {
                baas *= 10;
            }
            int[] countArray = new int[10];


            for (long id : temp){
                int kohal = (int)((id/ baas)%10);
                countArray[kohal] ++;
            }

            for (int j = 1; j < countArray.length; j++){
                countArray[j] += countArray[j-1];
            }

            for (int j = temp.length-1; j >= 0; j--){
                long id = temp[j];
                int kohal =  (int)((id/baas)%10);
                tulemus[countArray[kohal]-1] = id;
                countArray[kohal]--;
            }
            for (int j = 0; j < tulemus.length; j++) {
                temp[j] = tulemus[j];
            }
        }

        for (int i = 0; i < temp.length; i++) {
            isikukoodid.set(i, temp[i]);
        }
    }

    /**
     * Annab isikukoodi esimese numbri
     * @param n Antud isikukood, peab olema 11 numbri pikkune
     * @return Isikukoodi esimene number
     */
    public static int esimeneNumber(long n){
        return (int)(n/10000000000L);
    }

    /**
     * Annab isikukoodi, kust on eemaldatud esimene ja viimane number
     * @param n Antud isikukood, peab olema 11 numbri pikkune
     * @return Töödeldud arv
     */
    public static long keskmine(long n){
        return (n%10000000000L - n%10)/10;
    }
}
