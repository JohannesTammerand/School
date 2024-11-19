import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Kodu4a {

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
        List<Long>[] buckets = new ArrayList[4];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (long id : isikukoodid){
            int esimene = (int)(id/10000000000L);
            if (esimene % 2 == 0){
                buckets[(esimene/2)-1].add(id);
            } else {
                buckets[(esimene-1)/2].add(id);
            }
        }
        isikukoodid = new long[isikukoodid.length];
        int index = 0;
        for (List<Long> l : buckets){
            for (long id : l){
                isikukoodid[index] = id;
                index++;
            }
        }

        long[] tulemus = new long[isikukoodid.length];
        for (int i = 1; i < 11; i++) {
            long baas = 10;
            for (int j = 2; j < i; j++) {
                baas *= 10;
            }
            int[] countArray = new int[10];


            for (long id : isikukoodid){
                int kohal = (int)((id/ baas)%10);
                countArray[kohal] ++;
            }

            for (int j = 1; j < countArray.length; j++){
                countArray[j] += countArray[j-1];
            }

            for (int j = isikukoodid.length-1; j >= 0; j--){
                long id = isikukoodid[j];
                int kohal =  (int)((id/baas)%10);
                tulemus[countArray[kohal]-1] = id;
                countArray[kohal]--;
            }
            isikukoodid = Arrays.copyOf(tulemus, tulemus.length);
        }

        System.out.println(Arrays.toString(tulemus));
    }


    public static void main(String[] args) {
        /*
        long[] isikukoodid = new long[10];
        for (int i = 0; i < 10; i++) {
            isikukoodid[i] = genereeriIsikukood();
        }
        */


        long[] isikukoodid = new long[]{79410159545L, 73908077445L, 78909287261L, 71001128664L};


        System.out.println("Algne: " + Arrays.toString(isikukoodid));
        System.out.println("Sorteeritud:");
        long start = System.currentTimeMillis();
        sort(isikukoodid);
        long end = System.currentTimeMillis();
        //System.out.println(end - start);
    }
}
