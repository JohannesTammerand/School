/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2023/2024 kevadsemester
 *
 * Kodutöö nr 10
 * Teema: Sõned
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

import java.util.ArrayList;
import java.util.Arrays;

public class Kodu10 {

    /**Funktsiooni ülesandeks on sõnest eemaldada kõik samast sümbolist koosnevad alamsõned
     * Kui muudetud sõnes on ikka veel samast sümbolist koosnevaid alamsõnesid, tuleb need ka eemaldada
     * @param a Antud sõne
     * @return Muudetud sõne
     */
    public static String eemaldaKordusgrupid(String a) {
        StringBuilder b = new StringBuilder(a);
        ArrayList<int[]> kordused = new ArrayList<>(); // ArrayList, mis koosneb täisarvude paaridest, kus esimene arv näitab eemaldatava sõne algust ning teine selle lõppu

        boolean korduv = false;
        int kordusAlgus = -1;
        for (int i = 1; i < b.length(); i++) {
            if (b.charAt(i) == b.charAt(i-1) && !korduv){
                kordused.add(new int[] {i-1, -1});
                kordusAlgus = i-1;

                korduv = true;
            } else if (b.charAt(i) != b.charAt(i-1) && korduv) {
                kordused.set(kordused.size() - 1, new int[]{kordusAlgus, i - 1});
                kordusAlgus = -1;

                korduv = false;
            }
        }

        if (korduv){
            kordused.set(kordused.size()-1, new int[]{kordusAlgus, b.length()-1});
        }

        for (int i = kordused.size() - 1; i >= 0; i--) {
            b.delete(kordused.get(i)[0], kordused.get(i)[1] + 1);
        }

        String c = b.toString();

        if (kordused.isEmpty()){ //Kui sõne saab veel selle funktsiooniga muuta, teeb seda rekursiivselt
            return c;
        } else {
            return eemaldaKordusgrupid(c);
        }
    }

    /**
     * Funktsiooni ülesandeks on sõnes esinevate kaashäälikuühendite kokkulugemine
     * @param a antud sõne
     * @return Kaashäälikuühendite arv
     */
    public static int kaashäälikuÜhendeid(String a) {
        String kaashaalikud = "bcdfghjklmnpqrsšzžtvwxy";
        a = a.toLowerCase();

        boolean kaashaalik = false; //Kas tsükkel loeb praegu kaashäälikuühendit
        boolean samad = true; //Kas hetkel loetavas kaashäälikuühendis on kõik sümbolid samad
        int yhendeid = 0;
        int eelmisest = 0;
        for (int i = 0; i < a.length(); i++) {
            if (kaashaalikud.indexOf(a.charAt(i)) != -1){
                if (i > 0 && kaashaalik && a.charAt(i) != a.charAt(i-1)){
                    samad = false;
                }

                if (!kaashaalik){
                    kaashaalik = true;
                }

                eelmisest++;
            } else if (kaashaalik){
                if (eelmisest > 1 && !samad) {
                    yhendeid++;
                }
                kaashaalik = false;
                samad = true;
                eelmisest = 0;
            }
        }

        if (kaashaalik && eelmisest > 1 && !samad){
            yhendeid++;
        }

        return yhendeid;
    }

    //BOONUSÜLESANNE
    public static String[] kõikTulemused(String s){
        throw new UnsupportedOperationException("Kirjuta oma lahendus selle rea asemele. Juhul kui ülesanne jääb lahendamata jäta see rida alles.");
    }

    public static void main(String[] args) {

        System.out.println("Kodutöö nr 10. \t\t\t\t Programmi väljund");
        System.out.println("==============================================");

        String a = "aabbbaaacabbbbac";
        System.out.println("Sõne: " + a);
        System.out.println("Kordused eemaldatud: " + eemaldaKordusgrupid(a));

        a = "abbbaaacabb";
        System.out.println("Sõne: " + a);
        System.out.println("Kordused eemaldatud: " + eemaldaKordusgrupid(a));

        System.out.println(kaashäälikuÜhendeid("GgAkK"));

        System.out.println("==============================================");

        // Siin võib vabas vormis oma lahendust testida.
        // Automaattestid töid hinnates main meetodit ei käivita. (Samas peab siiski kompileeruma!)
    }
}