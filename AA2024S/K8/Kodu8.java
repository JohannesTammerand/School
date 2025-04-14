package K8;

/***********************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2024/2025 sügissemester
 *
 * Kodutöö nr 8
 * Teema: Toespuud
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

import java.util.ArrayList;
import java.util.Stack;

public class Kodu8 {

    /**
     * Tippude klass Galler-Fischeri klassipuude kasutamiseks
     */
    public static class Tipp {
        int idx;

        Tipp viit;

        Tipp (int idx){
            this.idx = idx;
            this.viit = this;
        }
    }

    /**
     * Leiab minimaalse kaugusega toesepuu Kruskali algoritmiga.
     *
     * @param nimed Asukohtade nimed
     * @param K     Asukohtade koordinaadid tabelina, kus iga rida on kujul [laiuskraad, pikkuskraad]
     * @return Minimaalse toesepuu servade loend, kujul [[i1, j2], [i2, j2], ...], kus i ja j on asukohtade indeksid alates 0-ist
     */
    public static int[][] toesKruskal(String[] nimed, double[][] K) {
        int linnadeArv = K.length;

        double[][] servad = new double[linnadeArv*(linnadeArv-1)/2][3]; //Servade loend kujul [[ots1, ots2, kaugus], [ots1, ots2, kaugus], ...]
        int servIdx = 0;
        for (int i = 0; i < linnadeArv; i++) {
            for (int j = i+1; j < linnadeArv; j++) {
                servad[servIdx] = new double[]{i, j, kaugus(K[i][0], K[i][1], K[j][0], K[j][1])};
                servIdx++;
            }
        }

        sorteeri(servad, 0, servad.length-1);
        Tipp[] tipud = new Tipp[K.length];
        for (int i = 0; i < tipud.length; i++) {
            tipud[i] = new Tipp(i);
        }

        ArrayList<int[]> minToespuu = new ArrayList<>();
        for (double[] s : servad){
            Tipp t1 = tipud[(int)s[0]];
            Tipp t2 = tipud[(int)s[1]];
            while (t1.viit != t1){
                t1 = t1.viit;
            }
            while (t2.viit != t2){
                t2 = t2.viit;
            }
            if (t1 != t2){
                t1.viit = t2;
                minToespuu.add(new int[]{tipud[(int)s[0]].idx, tipud[(int)s[1]].idx});
            }
        }

        int[][] minToespuuA = new int[minToespuu.size()][];
        for (int i = 0; i < minToespuuA.length; i++) {
            minToespuuA[i] = minToespuu.get(i);
        }

        return minToespuuA;
    }

    /**
     * Sorteerib toesKruskal meetodis loodud servade järjendi kiirmeetodiga
     * @param servad Servade järjend, mis vastab kujule [[ots1, ots2, kaugus], [ots1, ots2, kaugus], ...]
     * @param start Vaadeldava järjendi piirkonna algusindeks
     * @param end Vaadeldava järjendi piirkonna lõppindeks
     */
    public static void sorteeri(double[][] servad, int start, int end){
        if (start < end){
            double lahe = servad[end][2];
            int i = start-1;
            for (int j = start; j < end; j++) {
                if (servad[j][2] < lahe){
                    i++;
                    double[] temp = servad[i];
                    servad[i] = servad[j];
                    servad[j] = temp;
                }
            }

            double[] temp = servad[end];
            servad[end] = servad[i+1];
            servad[i+1] = temp;
            i++;

            sorteeri(servad, start, i-1);
            sorteeri(servad, i+1, end);
        }
    }

    /**
     * Leiab lähendi rändkaupmehe probleemile.
     *
     * @param nimed Asukohtade nimed
     * @param K     Asukohtade koordinaadid tabelina, kus iga rida on kujul [laiuskraad, pikkuskraad]
     * @return Rändkaupmehe lähend kui asukohtade läbimise järjestus arvude 0...n-1 permutatsioonina, kus n on tippude arv
     */
    public static int[] rändkaupmees(String[] nimed, double[][] K) {
        if (K.length == 1){
            return new int[]{0};
        }

        int[][] minToespuu = toesKruskal(nimed, K);
        int vaadeldav = 0;
        ArrayList<Integer> lahendL = new ArrayList<>();
        Stack<Integer> labitud = new Stack<>();
        lahendL.add(0);
        labitud.add(0);
        while (!labitud.isEmpty() || lahendL.size() <= minToespuu.length){
            int vaadeldavAlg = vaadeldav;
            for (int i = 0; i < minToespuu.length; i++) {
                int[] tee = minToespuu[i];
                if (tee != null && (tee[0] == vaadeldav || tee[1] == vaadeldav)){
                    vaadeldav = tee[0] == vaadeldav ? tee[1] : tee[0];
                    lahendL.add(vaadeldav);
                    minToespuu[i] = null;
                    break;
                }
            }
            if (vaadeldavAlg == vaadeldav){
                vaadeldav = labitud.pop();
            } else {
                labitud.add(vaadeldavAlg);
            }
        }


        int[] lahendA = new int[lahendL.size()];
        for (int i = 0; i < lahendA.length; i++) {
            lahendA[i] = lahendL.get(i);
        }

        return lahendA;
    }

    /**
     * Leiab kahe punkti vahelise kauguse, kasutades valemit siit:
     * https://en.wikipedia.org/wiki/Haversine_formula
     *
     * @param lai1 Esimese punkti laiuskraad
     * @param pik1 Esimese punkti pikkuskraad
     * @param lai2 Teise punkti laiuskraad
     * @param pik2 Teise punkti pikkuskraad
     * @return Punktide vaheline kaugus kilomeetrites
     */
    public static double kaugus(double lai1, double pik1, double lai2, double pik2) {
        double dLaius = Math.pow(Math.sin(Math.toRadians(lai2 - lai1) / 2), 2);
        return 2 * 6371.0088 * Math.asin(Math.sqrt(dLaius +
                (1 - dLaius - Math.pow(Math.sin(Math.toRadians(lai1 + lai2) / 2), 2)) *
                        Math.pow(Math.sin(Math.toRadians(pik2 - pik1) / 2), 2)));
    }

    public static void main(String[] args) {
        String[] nimed = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        double[][] K = new double[][]{{58.1367656942309, 26.9791809037222}, {57.995742286077565, 27.027699732007424}, {58.04526673036693, 27.055851292597556}, {58.051239236783765, 27.31838613585345}, {57.88898943419343, 27.323230506366844}, {58.0467919617484, 26.913627884291397}, {57.91191560141035, 26.83863676973282}, {58.25363083683729, 27.06614309376861}, {58.02672207776118, 27.17443324079925}, {58.16901967590721, 26.939358020920796}, {58.103588114971785, 26.98058065602831}, {58.25675744855752, 27.302402005390793}, {58.12634117337024, 26.98305848259398}, {58.16768800813021, 27.378566556498505}, {57.912008905074565, 27.304914483573913}, {58.13232090684732, 27.048974553543744}, {57.96784487491283, 27.105614874363344}, {58.135983233350146, 26.79938941407243}, {58.06568108965556, 26.72614162267317}, {57.83054364088893, 27.064109638134962}, {58.14615850685719, 26.99890598567821}, {58.29163595785651, 26.990647638530284}, {58.14165752746984, 27.27629517071277}, {58.26809903188161, 27.14923886092438}, {58.30993397259175, 26.76298695493574}, {58.20742093316571, 26.912332592895233}, {58.27073313220923, 26.98267114945}, {58.03199679955499, 26.951939998639673}, {57.97272695490563, 27.033677532454544}, {57.86114053661354, 27.257556502284636}, {57.899106447498944, 27.30215013917636}, {57.8832534043616, 26.774729342688126}, {57.82571955709874, 27.111852075619634}, {57.95591516032559, 27.422155851892256}, {58.273435203543286, 26.96054068312855}, {57.81909975647241, 27.30526191140864}, {58.07801212454884, 27.32418498706109}, {58.10962997080724, 27.215428188645827}, {58.09581311610783, 26.79161058508939}, {58.11220511583712, 27.36859304356517}, {58.13157692944269, 26.98914494260982}, {58.2729882465948, 27.32310714729243}, {57.944687637204424, 27.41826254937843}, {58.01476446302847, 27.13050760516682}, {57.94365256114022, 27.314257593061992}, {58.148265820835775, 27.160543002359294}, {58.06102289915866, 26.973516971782857}, {58.18847132017082, 27.33914340105072}, {57.985278526206486, 27.267088992729548}, {58.18239778681982, 27.082408605352036}, {58.207568504403106, 26.984337667083093}, {58.25217022184002, 27.22197778613987}, {57.89363215528348, 27.298204466386604}, {58.18207702754213, 27.294365168591778}, {58.23197350531757, 27.335384137644624}, {58.31449787986078, 26.801513227765966}, {58.125260058530316, 27.10781004072919}, {57.88984672351926, 27.101666550974134}, {58.09204326827648, 26.949153284559067}, {58.179360093628674, 26.993005720203364}, {57.8197073446517, 26.78209645531888}, {57.86491932010702, 26.83415960081756}, {58.26993129631549, 27.016001049977437}, {58.24433064376153, 26.955038940695413}, {58.02785988705995, 26.744717608860896}, {58.11954525680014, 26.828934216599688}, {58.05797902010517, 27.129927974853345}, {58.07587218649336, 27.232396954353252}, {58.157045802770625, 27.335996439240386}, {58.212120821915875, 26.920527642139593}, {58.00045251379597, 27.0800262324425}, {57.95705016232486, 27.4277171641606}, {58.10373244658468, 26.796631385496763}, {58.033677484561274, 27.112469794915853}, {58.10619801997176, 27.244514818415546}, {58.11605510509287, 27.311525870545264}, {58.262853960706295, 26.981923038548757}, {58.14171904827833, 27.327838851748954}, {57.8613484417576, 27.382045893666938}, {58.124917019032814, 27.139968524513698}, {57.89410695986188, 27.445993465304696}};
        System.out.println(toesKruskal(nimed, K).length);
        rändkaupmees(nimed, K);
    }
}