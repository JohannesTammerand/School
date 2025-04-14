//package K6;
//
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//public class Kodu6a {
//
//    public static Tipp koostaKoodipuu(String sisu) {
//        // a) teksti põhjal luua sümbolite sagedustabel;
//        // b) sagedustabeli põhjal luua loetelu ühetipulistest puudest (igas tipus on siis info kujul sümbol + selle esinemiste arv)
//        // c) selliste puude metsast Huffmani koodipuu loomine (koodipuu lehttippudes peaks olema .info välja väärtuseks vastav sümbol)
//
//        List<Tipp> jarjekord = new ArrayList<>();
//        for (int i = 0; i < sisu.length(); i++) {
//            String s = Character.toString(sisu.charAt(i));
//            Boolean leitud = false;
//            System.out.println(jarjekord.size());
//            for (Tipp t : jarjekord){
//                if (Objects.equals(t.info, s)){
//                    t.x += 1;
//                    leitud = true;
//                    break;
//                }
//            }
//            if (!leitud) {
//                Tipp t1 = new Tipp(s);
//                t1.x = 1;
//                jarjekord.add(t1);
//            }
//        }
//
//        List<Tipp> minHeap = new ArrayList<>();
//        for (Tipp t : jarjekord){
//            lisaPuule(minHeap, t);
//        }
//
//        Tipp r1 = null;
//        for (int i = 0; i < jarjekord.size(); i++) {
//            Tipp r = new Tipp(null);
//            if ()
//        }
//
//        return null;
//    }
//
//    public static List<Tipp> lisaPuule(List<Tipp> puu, Tipp t){
//        puu.add(t);
//        int idx = puu.size()-1;
//        while (idx > 0 && puu.get((idx-1)/2).x > puu.get(idx).x){
//            Tipp temp = puu.get(idx);
//            puu.set(idx, puu.get((idx-1)/2));
//            puu.set((idx-1)/2, temp);
//            idx = (idx-1)/2;
//        }
//        return puu;
//    }
//
//    public static List<Tipp> emmaldaKirje(List<Tipp> puu, Tipp t){
//        int idx = -1;
//        for (int i = 0; i < puu.size(); i++) {
//            if (puu.get(i) == t){
//                puu.set(i, puu.getLast());
//                idx = i;
//                break;
//            }
//        }
//
//        puu.removeLast();
//
//        while (true){
//            int v = 2*idx+1;
//            int p = 2*idx+2;
//            int vaikseim = idx;
//            if (v < puu.size() && puu.get(v).x < puu.get(idx).x){
//                vaikseim = v;
//            }
//            if (p < puu.size() && puu.get(p).x < puu.get(idx).x){
//                vaikseim = p;
//            }
//            if (vaikseim != idx){
//
//            }
//        }
//    }
//
//
//    public static boolean[] kodeeri(Tipp koodipuu, String sisu) {
//        // d) leida koodipuust igale sümbolile vastav uus kood -> prefikskood
//        // e) algtekst, prefikskoodid, koodipuu -> pakitud tekst (bittide massiiv, kus true on 1 ja false on 0)
//
//        throw new UnsupportedOperationException();
//    }
//
//    public static String dekodeeri(Tipp koodipuu, boolean[] bitid) {
//        // f) koodipuu, pakitud tekst -> lahtipakitud tekst
//
//        throw new UnsupportedOperationException();
//    }
//
//    public static Tipp lisaKirje(Tipp juur, String väärtus) {
//        if (juur == null){
//            Tipp temp = new Tipp(väärtus);
//            temp.x = 1;
//            return temp;
//        }
//
//
//
//        return juur;
//    }
//
//    public static void main(String[] args) throws IOException {
//        // Näide meetodite rakendamisest
//
//        // Tekstifailist algse teksti lugemine
//        String sisu = Files.readString(new File("Kõrboja_peremees.txt").toPath());
//
//        // Koodipuu koostamine, kodeerimine ja dekodeerimine
//        Tipp koodipuu = koostaKoodipuu(sisu);
////        boolean[] bitid = kodeeri(koodipuu, sisu);
////        String dekodeeritud = dekodeeri(koodipuu, bitid);
////        System.out.println(dekodeeritud);
//
//        // Näide abiklassi FailiSisu kasutamisest
//
//        // Andmete faili kirjutamine
//        // Tipp koodipuu = koostaKoodipuu(sisu);
//        // boolean[] bitid = kodeeri(koodipuu, sisu);
//        // FailiSisu.kirjutaFaili(new File("kodeeritud.dat"), koodipuu, bitid);
//
//        // Andmete failist lugemine
//        // FailiSisu failiSisu = FailiSisu.loeFailist(new File("kodeeritud.dat"));
//        // String dekodeeritud = dekodeeri(failiSisu.koodipuu, failiSisu.bitid);
//        // System.out.println(dekodeeritud);
//    }
//
//}