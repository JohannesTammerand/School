/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2023/2024 kevadsemester
 *
 * Kodutöö nr 1a
 * Teema: Algarvuringide leidmine
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class Kodu1a {
    /**
     * Funktsioon leiab antud löhtekohast n 5 suurima liikmega algarvuringiga arvu
     * ning väljastab leitud suurimad arvud
     * @param n antud algarvude lähtekoht
     */
    static void algarvuRingid5Suurimat(int n) {
        int[] tulemused = new int[5];
        int lahtekoht = n;
        for(; n>9; n--){
            int suurim = algaarvuRingiSuurim(n, lahtekoht);
            if (suurim > -1){
                for (int i = 0; i < tulemused.length; i++) {
                    boolean korduv = false;
                    for (int nr : tulemused){
                        if (nr == suurim) {
                            korduv = true;
                        }
                    }
                    if (tulemused[i] == 0 && !korduv){
                        tulemused[i] = suurim;
                        break;
                    }
                }
            }
        }

        tulemused = sorteeri(tulemused);

        System.out.println("\nAntud lähtekoht: " + lahtekoht);
        System.out.println("Leitud algarvude suurimad: ");
        for (int tulemus: tulemused){
            if(tulemus != 0) {
                System.out.println(tulemus);
            }
        }
    }

    /**
     * Abifunktsioon täisarvude massiivi sorteerimiseks kahanevaks
     * Funktsiooni põhimõte võetud kursuse 2. loengust
     * @param massiiv Massiiv sorteerimiseks
     * @return Tagastab uue sorteeritud massiivi
     */
    static int[] sorteeri(int[] massiiv){
        int i;
        for (int k = 0; k < massiiv.length-1; k++){
            i = leiaSuurim(massiiv, k);

            int suurim = massiiv[i];
            massiiv[i] = massiiv[k];
            massiiv[k] = suurim;
        }
        return massiiv;
    }

    /**
     * Leiab massiivi suurima elemendi indeksi, mis asub peale indeksit i
     * @param massiiv
     * @param i
     * @return Suurima elemendi indeks
     */
    static int leiaSuurim(int[] massiiv, int i){
        for (int j = i + 1; j < massiiv.length; j++) {
            if(massiiv[j] > massiiv[i]){
                i = j;
            }
        }
        return i;
    }

    /**
     * Leiab lähtekohast n väiksemate arvude koguse, millel on algarvuring ning mille algarvuringide
     * mitte ükski element ei ole suurem kui n
     * @param n Lähtekoht
     * @return Algarvuringide arv
     */
    static int algarvuRingideArv(int n) {
        int tulemus = 0;
        int lahtekoht = n;
        int[] tulemused = new int[n];

        for (; n > 9; n--) {
            int suurim = algaarvuRingiSuurim(n, lahtekoht);
            if (suurim > -1){
                boolean korduv = false;
                for(int nr : tulemused){
                    if (suurim == nr){
                        korduv = true;
                        break;
                    } else if(nr == 0){
                        break;
                    }
                }
                if(!korduv){
                    tulemus++;
                    for (int i = 0; i < tulemused.length; i++) {
                        if (tulemused[i] == 0) {
                            tulemused[i] = suurim;
                            break;
                        }
                    }
                }
            }
        }

        return tulemus;
    }

    /**
     * Leiab, kas arvul esineb algarvuring, mille puhul tagastab selle algarvuringi suurima arvu
     * Algarvuringi ei loeta, kui selle mingi element on antud lähtekohast suurem
     * @param n Arv, millest algarvuringi leida
     * @param lahtekoht Lähtekoht
     * @return Kui on algarvuring, siis selle suurim arv. Vastasel juhul tagastab -1
     */
    static int algaarvuRingiSuurim(int n, int lahtekoht){
        int arvuPikkus = 0;
        int n2 = n;
        for(; n2 > 0; n2/=10){arvuPikkus++;}

        boolean algarvuRing = true;
        int suurim = n;
        for (int i = 0; i < arvuPikkus && algarvuRing; i++) {
            int uusArv;
            if (i != 0) {
                int viimane = n % (int)Math.pow(10, i);
                uusArv = (int) (n / Math.pow(10, i) + viimane * Math.pow(10, arvuPikkus - i)); //Võimaldab arvude tsüklilise ümberpaigutamise
                if (uusArv > suurim){suurim = uusArv;}
            } else {
                uusArv = n;
            }

            if (uusArv > lahtekoht){
                algarvuRing = false;
                break;
            }

            //Kontrollib, kas arv on algarv
            if (uusArv%2 != 0){ //Ainus paarisarvuline algarv on 2
                for (int j = 3; j <= Math.sqrt(uusArv); j+= 2) { //Kuna paarisarvud siiani ei jõua, pole vaja ka proovida neid
                    if (uusArv%j == 0){                          //teiste paarisarvudega jagada
                        algarvuRing = false;                     //Ruutjuure kasutamise idee saidilt: https://www.geeksforgeeks.org/prime-numbers/
                        break;
                    }
                }
            } else if (uusArv != 2) {
                algarvuRing = false;
                break;
            }
        }

        if (algarvuRing){
            return suurim;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        System.out.println("Kodutöö nr 1a. \t\t\t\t Programmi väljund");
        System.out.println("==============================================");
        algarvuRingid5Suurimat(106);
        algarvuRingid5Suurimat(1000);

        System.out.println("\nAntud lähtkoht: " + 100);
        System.out.println("Väiksemaid erinevaid alharvuringe: " + algarvuRingideArv(100));

        System.out.println("\nAntud lähtkoht: " + 1000);
        System.out.println("Väiksemaid erinevaid alharvuringe: " + algarvuRingideArv(1000));
        System.out.println("==============================================");
    }
}
