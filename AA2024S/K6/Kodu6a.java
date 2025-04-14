package K6;

/***********************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2024/2025 kevadsemester
 *
 * Kodutöö nr 6a
 * Teema: Kahendkuhjad
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Kodu6a {

    /**
     * Meetod koodipuu loomiseks sõnest
     * @param sisu Sõne
     * @return koodipuu
     */
    public static Tipp koostaKoodipuu(String sisu) {
        // a) teksti põhjal luua sümbolite sagedustabel;
        // b) sagedustabeli põhjal luua loetelu ühetipulistest puudest (igas tipus on siis info kujul sümbol + selle esinemiste arv)
        // c) selliste puude metsast Huffmani koodipuu loomine (koodipuu lehttippudes peaks olema .info välja väärtuseks vastav sümbol)

        List<Tipp> jarjekord = new ArrayList<>();
        for (int i = 0; i < sisu.length(); i++) {
            String s = Character.toString(sisu.charAt(i));
            Boolean leitud = false;
            for (Tipp t : jarjekord){
                if (Objects.equals(t.info, s)){
                    t.x += 1;
                    leitud = true;
                    break;
                }
            }
            if (!leitud) {
                Tipp t1 = new Tipp(s);
                t1.x = 1;
                jarjekord.add(t1);
            }
        }

        List<Tipp> minHeap = new ArrayList<>();
        for (Tipp t : jarjekord){
            lisaPuule(minHeap, t);
        }

        Tipp r = null;
        for (int i = 0; i < jarjekord.size()-1; i++) {
            r = new Tipp(null);
            r.v = minHeap.get(0);
            r.x += minHeap.get(0).x;
            if (minHeap.size() >= 3){
                if (minHeap.get(1).x <= minHeap.get(2).x){
                    r.p = minHeap.get(1);
                    r.x += minHeap.get(1).x;
                    minHeap = eemaldaKirje(minHeap, 1);
                } else {
                    r.p = minHeap.get(2);
                    r.x += minHeap.get(2).x;
                    minHeap = eemaldaKirje(minHeap, 2);
                }
            } else if (minHeap.size() == 2){
                r.p = minHeap.get(1);
                r.x += minHeap.get(1).x;
                minHeap = eemaldaKirje(minHeap, 1);
            }
            minHeap = eemaldaKirje(minHeap, 0);
            minHeap = lisaPuule(minHeap, r);
        }

        return r;
    }

    /**
     * Lisa kirja kuhjale
     * @param puu Kuhi
     * @param t Kirje
     * @return Puu
     */
    public static List<Tipp> lisaPuule(List<Tipp> puu, Tipp t){
        puu.add(t);
        int idx = puu.size()-1;
        while (idx > 0 && puu.get((idx-1)/2).x > puu.get(idx).x){
            Tipp temp = puu.get(idx);
            puu.set(idx, puu.get((idx-1)/2));
            puu.set((idx-1)/2, temp);
            idx = (idx-1)/2;
        }
        return puu;
    }

    /**
     * Eemaldab kirje kuhjast
     * @param puu Kuhi
     * @param idx Kirje indeks
     * @return Kuhi
     */
    public static List<Tipp> eemaldaKirje(List<Tipp> puu, int idx){
        puu.set(idx, puu.get(puu.size()-1));
        puu.remove(puu.size()-1);

        while (true){
            int v = 2*idx+1;
            int p = 2*idx+2;
            int vaikseim = idx;
            if (v < puu.size() && puu.get(v).x < puu.get(idx).x){
                vaikseim = v;
            }
            if (p < puu.size() && puu.get(p).x < puu.get(idx).x){
                vaikseim = p;
            }
            if (vaikseim != idx){
                Tipp temp = puu.get(idx);
                puu.set(idx, puu.get(vaikseim));
                puu.set(vaikseim, temp);
                idx = vaikseim;
            } else {
                break;
            }
        }

        return puu;
    }

    /**
     * Kodeerib sõne bitivektroiks koodipuud kasutades
     * @param koodipuu Koodipuu
     * @param sisu Sõne
     * @return Bitivektor
     */
    public static boolean[] kodeeri(Tipp koodipuu, String sisu) {
        // d) leida koodipuust igale sümbolile vastav uus kood -> prefikskood
        // e) algtekst, prefikskoodid, koodipuu -> pakitud tekst (bittide massiiv, kus true on 1 ja false on 0)

        TreeMap<String, List<Boolean>> koodiTabel = teeKoodid(koodipuu, new ArrayList<>());
        List<Boolean> kood = new ArrayList<>();
        for (int i = 0; i < sisu.length(); i++) {
            kood.addAll(koodiTabel.get(String.valueOf(sisu.charAt(i))));
        }

        boolean[] koodArr = new boolean[kood.size()];
        for (int i = 0; i < kood.size(); i++) {
            koodArr[i] = kood.get(i);
        }

        return koodArr;
    }

    /**
     * Dekodeerib bitivektori sõneks koodipuud kasutades
     * @param koodipuu Koodipuu
     * @param bitid Bitivektor
     * @return Sõne
     */
    public static String dekodeeri(Tipp koodipuu, boolean[] bitid) {
        // f) koodipuu, pakitud tekst -> lahtipakitud tekst

        StringBuilder sisu = new StringBuilder();
        Tipp vaadeldav = koodipuu;
        for (int i = 0; i < bitid.length; i++){
            if (vaadeldav.info != null){
                sisu.append(vaadeldav.info);
                vaadeldav = koodipuu;
                i--;
            } else if (!bitid[i]){
                vaadeldav = vaadeldav.v;
            } else {
                vaadeldav = vaadeldav.p;
            }
        }

        sisu.append(vaadeldav.info);

        return sisu.toString();
    }

    /**
     * Leiab koodipuust iga tähe koodi ja lisab need paisktabelisse
     * @param juur Koodipuu
     * @param teekond Läbitud teekond läbi koodipuu
     * @return paisktabel
     */
    public static TreeMap<String, List<Boolean>> teeKoodid(Tipp juur, List<Boolean> teekond){
        TreeMap map = new TreeMap<String, List<Boolean>>();
        if (juur.info != null){
            map.put(juur.info, new ArrayList<>(teekond));
            return map;
        }

        teekond = new ArrayList<>(teekond);
        teekond.add(false);
        map.putAll(teeKoodid(juur.v, teekond));
        teekond.set(teekond.size()-1, true);
        map.putAll(teeKoodid(juur.p, teekond));

        return map;
    }

    public static void main(String[] args) throws IOException {
        // Näide meetodite rakendamisest

        // Tekstifailist algse teksti lugemine
        String sisu = Files.readString(new File("C:\\Users\\johan\\school\\AA2024S\\K6\\Korboja_peremees.txt").toPath());

        // Koodipuu koostamine, kodeerimine ja dekodeerimine
        Long start = System.currentTimeMillis();
        Tipp koodipuu = koostaKoodipuu(sisu);
        boolean[] bitid = kodeeri(koodipuu, sisu);
        String dekodeeritud = dekodeeri(koodipuu, bitid);
        System.out.println(dekodeeritud);
        System.out.println(System.currentTimeMillis()-start);

        // Näide abiklassi FailiSisu kasutamisest

        // Andmete faili kirjutamine
        // Tipp koodipuu = koostaKoodipuu(sisu);
        // boolean[] bitid = kodeeri(koodipuu, sisu);
        // FailiSisu.kirjutaFaili(new File("kodeeritud.dat"), koodipuu, bitid);

        // Andmete failist lugemine
        // FailiSisu failiSisu = FailiSisu.loeFailist(new File("kodeeritud.dat"));
        // String dekodeeritud = dekodeeri(failiSisu.koodipuu, failiSisu.bitid);
        // System.out.println(dekodeeritud);
    }

}
>>>>>>> 29cbe7ae5133348fa0ce2ce0bc961d286ff89d4a
