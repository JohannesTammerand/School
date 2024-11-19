public class Kodu5 {

    public static KOTipp lisaKirje(KOTipp juur, int väärtus) {
        juur = lisaRek(juur, väärtus);

        return juur;
    }

    public static KOTipp lisaRek(KOTipp juur, int väärtus){
        if (juur == null){
            KOTipp temp = new KOTipp(väärtus);
            temp.x = 1;
            return temp;
        }

        if (väärtus >= juur.väärtus){
            juur.p = lisaRek(juur.p, väärtus);
            if (juur.v != null){
                juur.v.x = leiaTasakaal(juur.v);
                juur.x = juur.v.x - juur.p.x;
            } else {
                juur.x = juur.p.x + 1;
            }
        } else {
            juur.v = lisaRek(juur.v, väärtus);
            if (juur.p != null){
                juur.p.x = leiaTasakaal(juur.p);
                juur.x = juur.v.x - juur.p.x;
            } else {
                juur.x = juur.v.x + 1;
            }
        }
        return juur;
    }

    public static int leiaTasakaal(KOTipp juur){
        if (juur == null){
            return 0;
        }

        if (juur.p != null && juur.v != null){
            return Math.max(leiaTasakaal(juur.p), leiaTasakaal(juur.v));
        } else if (juur.p != null){
            return leiaTasakaal(juur.p) + 1;
        } else if (juur.v != null){
            return leiaTasakaal(juur.v) + 1;
        } else {
            return 1;
        }
    }


    public static KOTipp eemaldaKirje(KOTipp juur, int väärtus) {
        throw new UnsupportedOperationException();
    }

    public static KOTipp liidaAVLpuud(KOTipp avl1, KOTipp avl2) {
        throw new UnsupportedOperationException();
    }

    public static String prindi(KOTipp juur){
        String s = "";
        s += juur.väärtus;

        if (juur.v != null && juur.p != null){
            s += "(" + prindi(juur.v) + "," + prindi(juur.p) + ")";
        } else if (juur.v != null){
            s += "(" + prindi(juur.v) + ")";
        } else if (juur.p != null){
            s += "(" + prindi(juur.p) + ")";
        }
        return s;
    }

    public static void main(String[] args) {
        /*
        KOTipp j1 = new KOTipp(1);
        KOTipp j2 = new KOTipp(2);
        KOTipp j3 = new KOTipp(3);
        KOTipp j4 = new KOTipp(4);
        KOTipp j5 = new KOTipp(5);
        KOTipp j6 = new KOTipp(6);
        KOTipp j7 = new KOTipp(7);
        KOTipp j8 = new KOTipp(8);
        KOTipp j9 = new KOTipp(9);

        j8.p = j9;
        j8.v = j6;
        j6.p = j7;
        j6.v = j2;
        j2.v = j1;
        j2.p = j4;
        j4.v = j3;
        j4.p = j5;
         */

        KOTipp juur = new KOTipp(40);
        lisaKirje(juur, 35);
        lisaKirje(juur, 36);
        lisaKirje(juur, 60);
        lisaKirje(juur, 44);
        lisaKirje(juur, 41);
        lisaKirje(juur, 55);
        lisaKirje(juur, 44);
        lisaKirje(juur, 43);
        lisaKirje(juur, 45);

        System.out.println(prindi(juur));
        System.out.println("----------");
        KOTipp juur2 = lisaKirje(juur, 10);
        System.out.println(prindi(juur2));
        System.out.println("a");
    }
}