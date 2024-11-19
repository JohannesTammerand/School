import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2023/2024 kevadsemester
 *
 * Kodutöö nr 12b
 * Teema: Sõned
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class Kodu12b {
    /**
     * Meetodi ülesandeks on leida neljatäheliste nimisõnadega sõnastikust lühim tee ühest
     * sõnast teiseni, kus iga sõna erineb eelmisest ja järgmisest ühe tähe poolt
     * Ülesannet sooritatakse abifunktsiooni lühimTuletusAbi abil
     * @param sõnad Sõnastik
     * @param ls Lähtesõna
     * @param ss Sihtsõna
     * @return Leitud lühim teekond
     */
    public static String[] lühimTuletus(String[] sõnad, String ls, String ss) {

        if (ss == ls){
            return new String[] {ss};
        }

        ArrayList<String> a1 = new ArrayList<>();
        a1.add(ls);
        ArrayList<String> a = new ArrayList<>();
        for (int i = 2; i < sõnad.length; i++) {
            a = lühimTuletusAbi(a1, sõnad, ss, i);
            if (!a.isEmpty()){
                break;
            }
        }


        String[] b = new String[a.size()];
        b = a.toArray(b);
        return b;
    }

    /**
     * Abifunktsioon lühimTuletus funktsioonile
     * @param a Senine leitud teekond
     * @param sõnad Sõnastik
     * @param ss Sihtsõna
     * @param maxPikkus Kui palju pikem saab teekond veel olla
     * @return Leitud teekond
     */
    public static ArrayList<String> lühimTuletusAbi(ArrayList<String> a, String[] sõnad, String ss, int maxPikkus){
        if (maxPikkus == 0){
            return a;
        }
        int erinev = 0;
        String viimane = a.get(a.size() - 1);
        ArrayList<String> b;
        String s = "";
        for (int i = -1; i < sõnad.length; i++){
            if (i == -1){
                s = ss;
            } else {
                s = sõnad[i];
            }

            erinev = 0;
            for (int j = 0; j < 4; j++) {
                if(s.charAt(j) != viimane.charAt(j)){
                    erinev++;
                }

                if (erinev >= 2){
                    break;
                }
            }

            if (erinev == 1){

                int erinev2 = 0;
                for (int k = 0; k < 4; k++) {
                    if (s.charAt(k) != ss.charAt(k)){
                        erinev2++;

                        if (erinev2 >= 2){
                            break;
                        }
                    }

                }

                if (erinev2 == 1 && maxPikkus > 1 && i != -1){
                    a.add(s);
                    a.add(ss);
                    return a;
                } else if (i == -1){
                    a.add(ss);
                    return a;
                }

                ArrayList<String> c = (ArrayList)a.clone();
                c.add(s);
                if (!a.contains(s)) {
                    b = lühimTuletusAbi(c, sõnad, ss, maxPikkus-1);
                    if (b.contains(ss)){
                        return b;
                    }
                }
            }
        }

        return new ArrayList<>();


    }

    public static String[] sõnad(String failinimi) throws IOException {
        return Files.readAllLines(Path.of(failinimi)).toArray(String[]::new);
    }


    //BOONUSÜLESANNE
    public static String[] pikimLühimTuletus(String[] sõnad){
        throw new UnsupportedOperationException("Kirjuta oma lahendus selle rea asemele. Juhul kui ülesanne jääb lahendamata jäta see rida alles.");
    }

    public static void main(String[] args) {
        try {
            String[] sonad = sõnad("C:\\Users\\johan\\school\\proge2-2024K\\K12\\Nimisonad4.txt");
            String[] a = lühimTuletus(sonad, "piim", "liim");

            System.out.println(Arrays.toString(a));
        } catch (IOException e){
            System.out.println(e);
        }




        // Siin võib vabas vormis oma lahendust testida.
        // Automaattestid töid hinnates main meetodit ei käivita. (Samas peab siiski kompileeruma!)
    }
}
