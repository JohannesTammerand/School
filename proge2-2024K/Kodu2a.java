/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2023/2024 kevadsemester
 *
 * Kodutöö nr 2a
 * Teema: Ilmateave
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class Kodu2a {

    /**
     * Leiab aasta keskmise temperatuuri
     * @param kuupäev Kuupäevi sisaldavate sõnede massiiv
     * @param kellaaeg Kellaaegi sisaldavate sõnede massiiv
     * @param temperatuur Temperatuuri sisaldavate komaarvude massiiv
     * @return Aasta keskmine temperatuur
     */
    public static double aastaKesk(String[] kuupäev, String[] kellaaeg, double[] temperatuur) {
        double summa = 0;
        for(double temp : temperatuur){
            summa += temp;
        }
        double keskmine = summa / temperatuur.length;

        return keskmine;
    }

    /**
     * Leiab aasta madalaima ja kõrgeima temperatuuri
     * @param kuupäev Kuupäevi sisaldavate sõnede massiiv
     * @param kellaaeg Kellaaegi sisaldavate sõnede massiiv
     * @param temperatuur Temperatuuri sisaldavate komaarvude massiiv
     * @return Komaarvude massiiv, kus esimene arv on aasta madalaim temperatuur ning teine element kõrgeim temperatuur
     */
    public static double[] aastaMinMax(String[] kuupäev, String[] kellaaeg, double[] temperatuur) {
        double[] tulemus = {temperatuur[0], temperatuur[0]};
        for (double temp : temperatuur){
            if (temp < tulemus[0]){
                tulemus[0] = temp;
            }

            if (temp > tulemus[1]){
                tulemus[1] = temp;
            }
        }

        return tulemus;
    }

    /**
     * Leiab aasta pikima ajavahemiku, kus temperatuur vaid tõusis või langes
     * @param kuupäev Kuupäevi sisaldavate sõnede massiiv
     * @param kellaaeg Kellaaegi sisaldavate sõnede massiiv
     * @param temperatuur Temperatuuri sisaldavate komaarvude massiiv
     * @return Sõnede massiv, mille esimene element sisaldab ajavahemiku algust ja teine selle lõppu
     *         Sõned on kujul "YYYY-MM-DD HH:MM:SS"
     */
    public static String[] pikimKasvavKahanev(String[] kuupäev, String[] kellaaeg, double[] temperatuur) {
        int vahe = 1;
        int suurimVahe = vahe;
        boolean kasvav = temperatuur[1] > temperatuur[0];
        String kp = kuupäev[0];
        String kl = kellaaeg[0];
        String[] tulemus = new String[4];
        for (int i = 1; i < temperatuur.length; i++) {
            if ((temperatuur[i] > temperatuur[i-1]) == kasvav) {
                vahe++; //Temperatuuri mõõdeti pidevalt iga 5 minuti tagant
            } else {
                if (vahe > suurimVahe){
                    suurimVahe = vahe;
                    tulemus[0] = kp;
                    tulemus[1] = kl;
                    tulemus[2] = kuupäev[i];
                    tulemus[3] = kellaaeg[i-1];
                }
                kp = kuupäev[i];
                kl = kellaaeg[i-1];
                vahe = 0;

                kasvav = !kasvav;
            }
        }

        if (vahe > suurimVahe){
            tulemus[0] = kp;
            tulemus[1] = kl;
            tulemus[2] = kuupäev[kuupäev.length-1];
            tulemus[3] = kellaaeg[kellaaeg.length-1];
        }

        String[] vastus = new String[2];
        vastus[0] = tulemus[0] + " " + tulemus[1];
        vastus[1] = tulemus[2] + " " + tulemus[3];

        return vastus;
    }

    /**
     * Leiab iga kuu keskmise temperatuuri
     * @param kuupäev Kuupäevi sisaldavate sõnede massiiv
     * @param kellaaeg Kellaaegi sisaldavate sõnede massiiv
     * @param temperatuur Temperatuuri sisaldavate komaarvude massiiv
     * @return Komaarvude massiiv, kus on järjestikku kuude keskmised temperatuurid, alates jaanuarist
     */
    public static double[] kuudeKeskmised(String[] kuupäev, String[] kellaaeg, double[] temperatuur) {
        double[] vastus = new double[12];
        vastus[0] = temperatuur[0];

        int kuu = 1;
        int lugemisteArv = 1;
        for (int i = 1; i < temperatuur.length; i++) {
            if (Integer.parseInt(kuupäev[i].split("-")[1]) > kuu){
                vastus[kuu-1] /= lugemisteArv;
                lugemisteArv = 0;
                kuu++;
            }

            vastus[kuu-1] += temperatuur[i];
            lugemisteArv++;
        }
        vastus[11] /= lugemisteArv;
        return vastus;
    }

    // Näidismeetod (mitte kasutamiseks):

    static double temperatuurValitudPäevalJaKellal(String[] kuupäev, String[] kellaaeg, double[] temperatuur,
                                                   String päev, String kell) {
        // Leiab ja tagastab antud ajal mõõdetud temperatuuri
        for (int i = 0; i < temperatuur.length; i++) {
            if (kuupäev[i].equals(päev) && kellaaeg[i].equals(kell))
                return temperatuur[i];
        }
        return Double.NaN;
    }

    public static void main(String[] args) {
        TemperatuuriAndmed andmed = TemperatuuriAndmed.loeAndmed("ilmAegTemp_2022.txt");
        String[] kuupäev = andmed.kuupäev;
        String[] kellaaeg = andmed.kellaaeg;
        double[] temperatuur = andmed.temperatuur;

        System.out.println("Kodutöö nr 2a. \t\t\t\t Programmi väljund");
        System.out.println("==============================================");

        System.out.println("Aasta 2022 temperatuuri andmed: ");
        System.out.println("\nAasta keskmine temperatuur: " + aastaKesk(kuupäev, kellaaeg, temperatuur));

        double[] minMax = aastaMinMax(kuupäev, kellaaeg, temperatuur);
        System.out.println("\nAasta madalaim temperatuur: " + minMax[0]);
        System.out.println("Aasta kõrgeim temperatuur: " + minMax[1]);

        String[] pikimVahe = pikimKasvavKahanev(kuupäev, kellaaeg, temperatuur);
        System.out.println("\nPikim vahe kasvamas või kahanemas: " + pikimVahe[0] + " kuni " + pikimVahe[1]);

        double[] kuudeKeskmised = kuudeKeskmised(kuupäev, kellaaeg, temperatuur);
        System.out.println("\nKuude keskmised temperatuurid järjest: ");
        for (double keskmine : kuudeKeskmised){
            System.out.println(keskmine);
        }

        System.out.println("==============================================");
    }

}
