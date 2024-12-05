import java.util.ArrayList;
import java.util.Arrays;

/***********************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2024/2025 kevadsemester
 *
 * Kodutöö nr 5
 * Teema: AVL-puud
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class Kodu5 {

    /**
     * Lisab kirje AVL-puusse
     * @param juur AVL-puu juur
     * @param väärtus Lisatav kirje
     * @return Uus AVL-puu
     */
    public static KOTipp lisaKirje(KOTipp juur, int väärtus) {
        if (juur == null){
            KOTipp temp = new KOTipp(väärtus);
            temp.x = 1;
            return temp;
        }

        int tasakaal;
        if (väärtus >= juur.väärtus){
            juur.p = lisaKirje(juur.p, väärtus);
            if (juur.v != null){
                juur.v.x = leiaKõrgus(juur.v);
                juur.x = Math.max(juur.v.x, juur.p.x) + 1;
                tasakaal = juur.v.x - juur.p.x;
            } else {
                juur.x = juur.p.x + 1;
                tasakaal = -juur.p.x;
            }
        } else {
            juur.v = lisaKirje(juur.v, väärtus);
            if (juur.p != null){
                juur.p.x = leiaKõrgus(juur.p);
                juur.x = Math.max(juur.v.x, juur.p.x) + 1;
                tasakaal = juur.v.x - juur.p.x;
            } else {
                juur.x = juur.v.x + 1;
                tasakaal = juur.v.x;
            }
        }

        if (tasakaal == 2){
            if (juur.v.p == null || (juur.v.v != null && leiaKõrgus(juur.v.v) - leiaKõrgus(juur.v.p) >= 0)){
                juur = paremPööre(juur);
            } else {
                juur.v = vasakPööre(juur.v);
                juur = paremPööre(juur);
            }
        } else if (tasakaal == -2){
            if (juur.p.v == null || (juur.p.p != null && leiaKõrgus(juur.p.v) - leiaKõrgus(juur.p.p) <= 0)){
                juur = vasakPööre(juur);
            } else {
                juur.p = paremPööre(juur.p);
                juur = vasakPööre(juur);
            }
        }

        return juur;
    }

    /**
     * Leiab puu kõrguse
     * @param juur Puu
     * @return Kõrgus
     */
    public static int leiaKõrgus(KOTipp juur){
        if (juur == null){
            return 0;
        }


        if (juur.p != null && juur.v != null){
            return Math.max(leiaKõrgus(juur.p), leiaKõrgus(juur.v)) + 1;
        } else if (juur.p != null){
            return leiaKõrgus(juur.p) + 1;
        } else if (juur.v != null){
            return leiaKõrgus(juur.v) + 1;
        } else {
            return 1;
        }
    }

    /**
     * Eemaldab kirje AVL-puust
     * @param juur Puu
     * @param väärtus Kirje
     * @return Uus puu
     */
    public static KOTipp eemaldaKirje(KOTipp juur, int väärtus) {
        if (juur == null){
            return juur;
        }

        if (juur.p != null && juur.p.väärtus == väärtus){
            juur.p = eemaldaKirje(juur.p, väärtus);
        } else if (juur.v != null && juur.v.väärtus == väärtus){
            juur.v = eemaldaKirje(juur.v, väärtus);
        } else if (juur.väärtus == väärtus){
            if (juur.p != null && juur.v != null){
                KOTipp temp = otsiJärgnev(juur.p, juur.väärtus);
                if (temp.väärtus != väärtus)eemaldaKirje(juur, temp.väärtus);
                if (temp.väärtus == väärtus)eemaldaKirje(juur.p, temp.väärtus);
                juur.väärtus = temp.väärtus;
            } else if (juur.p != null){
                return juur.p;
            } else if (juur.v != null){
                return juur.v;
            } else {
                return null;
            }
        }else {
            if (väärtus >= juur.väärtus){
                juur.p = eemaldaKirje(juur.p, väärtus);
            } else {
                juur.v = eemaldaKirje(juur.v, väärtus);
            }
        }

        int tasakaal = 0;
        if (juur.v != null && juur.p != null){
            tasakaal = leiaKõrgus(juur.v) - leiaKõrgus(juur.p);
        } else if (juur.v != null){
            tasakaal = leiaKõrgus(juur.v);
        } else if (juur.p != null){
            tasakaal = -leiaKõrgus(juur.p);
        }

        if (tasakaal == 2){
            if (juur.v.p == null || (juur.v.v != null && leiaKõrgus(juur.v.v) - leiaKõrgus(juur.v.p) >= 0)){
                juur = paremPööre(juur);
            } else {
                juur.v = vasakPööre(juur.v);
                juur = paremPööre(juur);
            }
        } else if (tasakaal == -2){
            if (juur.p.v == null || (juur.p.p != null && leiaKõrgus(juur.p.v) - leiaKõrgus(juur.p.p) <= 0)){
                juur = vasakPööre(juur);
            } else {
                juur.p = paremPööre(juur.p);
                juur = vasakPööre(juur);
            }
        }


        return juur;
    }

    /**
     * Otsib väärtusele järgneva väärtuse puust
     * @param juur Puu
     * @param väärtus Väärtus, millele järgnev otsida
     * @return Juur, mis on väärtusele järgnev
     */
    public static KOTipp otsiJärgnev(KOTipp juur, int väärtus){
        if (juur == null){
            return juur;
        }

        if (juur.väärtus == väärtus+1){
            return juur;
        }

        KOTipp r1 = null;
        KOTipp r2 = null;
        int min = juur.väärtus;
        if (juur.v != null){
            r1 = otsiJärgnev(juur.v, väärtus);
            min = Math.min(min, r1.väärtus);
        }
        if (juur.p != null){
            r2 = otsiJärgnev(juur.p, väärtus);
            min = Math.min(min, r2.väärtus);
        }

        if (min < väärtus) return null;
        if (juur.väärtus == min) return juur;
        if (r1.väärtus == min) return r1;
        if (r2.väärtus == min) return r2;
        return null;
    }

    /**
     * Liisab kaks AVL-puud kokku
     * @param avl1 Esimene puu
     * @param avl2 Teine puu
     * @return Kombineeritud puu
     */
    public static KOTipp liidaAVLpuud(KOTipp avl1, KOTipp avl2) {
        ArrayList<Integer> j1 = puuJärjendiks(avl1);
        ArrayList<Integer> j2 = puuJärjendiks(avl2);

        int i1 = 0;
        int i2 = 0;
        int[] kokku = new int[j1.size() + j2.size()];
        for (int i = 0; i < kokku.length; i++) {
            if (i1 < j1.size() && i2 < j2.size()){
                if (j1.get(i1) <= j2.get(i2)){
                    kokku[i] = j1.get(i1);
                    i1++;
                } else {
                    kokku[i] = j2.get(i2);
                    i2++;
                }
            } else if (i1 < j1.size()){
                kokku[i] = j1.get(i1);
                i1++;
            } else if (i2 < j2.size()){
                kokku[i] = j2.get(i2);
                i2++;
            }
        }

        return järjendPuuks(kokku, 0, kokku.length-1);
    }

    /**
     * Muudab puu järjendiks
     * @param juur Puu
     * @return Järjend
     */
    public static ArrayList<Integer> puuJärjendiks(KOTipp juur){
        if (juur == null){
            return new ArrayList<>();
        }

        ArrayList<Integer> j = new ArrayList<>();
        j.addAll(puuJärjendiks(juur.v));
        j.add(juur.väärtus);
        j.addAll(puuJärjendiks(juur.p));

        return j;
    }

    /**
     * Muudab järjendi AVL-puuks
     * @param a Täisarvude järjend
     * @param start Start-indeks
     * @param lõpp Lõpp-indeks
     * @return Puu
     */
    public static KOTipp järjendPuuks(int[] a, int start, int lõpp){
        if (start > lõpp){
            return null;
        }

        int keskmine = start+(lõpp - start)/2;
        KOTipp juur = new KOTipp(a[keskmine]);
        juur.v = järjendPuuks(a, start, keskmine-1);
        juur.p = järjendPuuks(a, keskmine+1, lõpp);

        return juur;
    }

    /**
     * Teeb puul parempöörde
     * @param juur Puu
     * @return Pööratud puu
     */
    public static KOTipp paremPööre(KOTipp juur){
        if (juur == null) return null;

        KOTipp juur1 = juur;
        juur = new KOTipp(juur1.v.väärtus);
        juur.p = new KOTipp(juur1.väärtus);
        juur.p.v = juur1.v.p;
        juur.v = juur1.v.v;
        juur.p.p = juur1.p;
        juur.x = leiaKõrgus(juur);
        return juur;
    }

    /**
     * Teeb puul vasakpöörde
     * @param juur Puu
     * @return Pööratud puu
     */
    public static KOTipp vasakPööre(KOTipp juur){
        if (juur == null) return null;

        KOTipp juur1 = juur;
        juur = new KOTipp(juur1.p.väärtus);
        juur.v = new KOTipp(juur1.väärtus);
        juur.v.v = juur1.v;
        juur.v.p = juur1.p.v;
        juur.p = juur1.p.p;
        juur.x = leiaKõrgus(juur);
        return juur;
    }
}