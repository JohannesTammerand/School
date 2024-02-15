/***********************************
 * Programmeerimine II. LTAT.03.007
 * 2022/2023 kevadsemester
 *
 * Kodutöö nr 1
 * Teema: Java-programm ja -meetodid; tsüklid
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

public class K1 {
    public static void main(String[] args) {
        /*
        boolean x = true;
        int i = 10;

        for (i = 17; x; i--) {
            if (i%7 == 0){
                x = false;
            }
        }

        for (int j = i; j > 10; j--){
            i *= 2;
        }

        System.out.println(i);

         */
        System.out.println(yl7());
    }

    /**
     * Nauraalarvu viimase kümnendnumbri viimine kõige ette
     * @param nr Antud arv
     * @return Arv, mille viimane number on viidud kõige ette
     */
    public static int yl2(int nr) {
        //Leiab arvu viimase kümnelise
        //nt: nr = 654 => i = 4
        int i = nr%10;

        //Leiab kõige suurema arvust väiksema kümne astme
        //nt: nr = 654 => j = 2 => 10^2 = 100
        int j;
        int abi = nr;
        for (j = 0; abi >= 10; j++) {
            abi /= 10;
        }

        //Viib arvu eelmise viimase kümnelise kõige ette ning tagastab lõpptulemuse
        //nt:
        //654/10 = 65
        //65 + 4*10^2 = 65 + 400 = 465
        return nr/10 + i*(int)(Math.pow(10, j));
    }

    /**
     * Teeb nullist suurema paarisarvulise natuuraalarvuga tehte f(x) = 1*2 + 3*4 + ... + (n-1)*n
     * @param nr Antud naturaalarv
     * @return Funktsiooni tulemus
     */
    public static int yl3(int nr){
        //f(n) = 1*2 + 3*4 + 5*6 + ... + (n-1)*n

        int summa = 0;
        for (int i = 1; i < nr; i+=2) {
            summa += i *(i+1);
        }

        return summa;
    }

    /**
     * Tehte 2*102 + 6*98 + 10*94 + ... + 46*58 + 50*54 väärtuse leidmine
     * @return Tehte väärtus
     */
    public static int yl4(){
        int summa = 0;
        for (int i = 2; i <= 50; i+=4) {
            summa += i*(104-i);
        }
        return summa;
    }

    /**
     * Võrrandi 4x + 3y - 9z = 5 lahendite leidmine
     * x, y ja z võivad omada väärtusi 0 kuni 100
     * @return Kõik võimalikud lahendid antud piirkonnas
     */
    public static String yl5(){
        String tulemus = "";
        for (int x = 0; x <= 100; x++) {
            for (int y = 0; y <= 100; y++) {
                for (int z = 0; z <= 100; z++) { //Käib läbi kõik võimalikud x, y ja z väärtused
                    if (4*x + 3*y - 9*z == 5){
                        tulemus += String.format("x= %1$s; y = %2$s; z = %3$s\n", x, y, z);
                    }
                }
            }
        }
        return tulemus;
    }

    /**
     * Leiab kõik neljakohalised arvud ABCD, mis rahuldavad tingimust (AB + CD)^2 = ABCD
     * @return Saadud arvud ABCD
     */
    public static String yl6(){
        String tulemus = "";
        for (int i = 1000; i <= 9999; i++) {
            if (i == Math.pow((i/100 + i%100), 2)){
                tulemus += Integer.toString(i) + "\n";
            }
        }
        return tulemus;
    }

    /**
     * Leiab kõik murrud kujul AB/BC, mis on võrdsed murruga A/C
     * A, B ja C peavad olema erinevad
     * @return Sõne, mis annab rea kaupa kõik leitud murrud
     */
    public static String yl7(){
        String tulemus = "";
        for (int a = 1; a <= 9; a++) {
            for (int b = 0; b <= 9; b++) {
                for (int c = 1; c <= 9; c++) { //c ei saa olla 0, sest muidu võib tulla nulliga jagamine
                    if((double)(a*10 + b)/(double)(b*10 + c) == ((double)a/(double)c) && a != b && a != c){
                        tulemus += String.format("%1$s/%2$s\n", (a*10 + b), (b*10 + c));
                    }
                }
            }
        }
        return tulemus;
    }

    /**
     * Koostab ülesandes nõutud mustrid
     * @param suurus Mustri suurus
     * @return Sõne, mis sisaldab loodud mustreid, mis on üksteisest eraldatud sidekriipsude ridadega
     */
    public static String yl8(int suurus){
        String tulemus = "";

        //a
        tulemus += "#".repeat(suurus) + "\n";
        for (int i = 2; i < suurus; i++) {
            tulemus += "#" + " ".repeat(suurus-2) + "#\n";
        }
        tulemus += "#".repeat(suurus) + "\n";

        tulemus += "-".repeat(suurus) + "\n";
        //----------------------------------------------------------------------
        //b
        tulemus += "#".repeat(suurus) + "\n";
        for (int i = 2; i < suurus; i++) {
            tulemus += " ".repeat(i-1) + "#" + " ".repeat(suurus-2) + "#\n";
        }
        tulemus += " ".repeat(suurus-1) + "#".repeat(suurus) + "\n";

        tulemus += "-".repeat(suurus) + "\n";
        //----------------------------------------------------------------------
        //c
        int j = 0;
        for (int i = 1; i <= suurus; i++) {
            tulemus += " ".repeat(j) + "#".repeat(i) + "\n";
            j += i;
        }

        tulemus += "-".repeat(suurus) + "\n";
        //----------------------------------------------------------------------
        //d
        int a = 0;
        for (int i = suurus; i > 1; i--) {
            a += i;
        }
        j = 2;
        for (int i = 1; i <= suurus; i++) {
            tulemus += " ".repeat(a) + "#".repeat(i) + "\n";
            a-=j;
            j++;
        }

        tulemus += "-".repeat(suurus) + "\n";
        //----------------------------------------------------------------------
        //e
        tulemus += "#".repeat(suurus) + "\n";
        if (suurus % 2 == 0){
            for (int i = 2; i < suurus; i+=2) {
                tulemus += "#".repeat(suurus/2 - i/2) + " ".repeat(i) + "#".repeat(suurus/2 - i/2) + "\n";
            }
        } else {
            for (int i = 1; i < suurus; i+=2) {
                tulemus += "#".repeat((suurus-i)/2) + " ".repeat(i) + "#".repeat((suurus-i)/2) + "\n";
            }
        }

        tulemus += "-".repeat(suurus) + "\n";
        //----------------------------------------------------------------------
        //f
        for (int i = suurus; i > 0 ; i--) {
            tulemus += " ".repeat(i) + "# ".repeat(suurus-i+1) + "\n";
        }

        tulemus += "-".repeat(suurus) + "\n";
        //----------------------------------------------------------------------
        //g
        tulemus += " ".repeat(suurus-1) + "#\n";
        for (int i = suurus-2; i >= 0 ; i--) {
            tulemus += " ".repeat(i) + "#" + " ".repeat(suurus-2-i) + "#\n";
        }
        for (int i = 1; i < suurus-1; i++) {
            tulemus += "#" + " ".repeat(suurus-2-i) + "#\n";
        }
        tulemus += "#\n";

        tulemus += "-".repeat(suurus) + "\n";
        //----------------------------------------------------------------------
        //h
        j = suurus-2;
        for (int i = suurus/2; i > 0 ; i--) {
            tulemus += " ".repeat(suurus/2-i) + "#" + " ".repeat(j) + "#\n";
            j -= 2;
        }
        if (suurus%2 != 0){
            tulemus += " ".repeat(suurus/2) + "#\n";
        }
        for (int i = 0; i < suurus/2; i++) {
            j += 2;
            tulemus += " ".repeat(suurus/2-i-1) + "#" + " ".repeat(j) + "#\n";
        }

        return tulemus;
    }

    /**
     * Leiab umbeskaudse pii väärtuse, kasutades meetodit, kus genereeritakse suvalise asukohaga punkte ruudus
     * ning vaadatakse, mis on ruudu siseringi seesolevate ja väljasolevate punktide suhe
     * @return Sõne, kus on kirjas genereeritud punktide arv ning nendest saadud pii väärtus
     */
    public static String yl9(){
        double x;
        double y;
        String tulemus = "";
        for (int i = 1; i < 9; i++) {
            int summaSees = 0;
            int summaValjas = 0;
            for (int j = (int)Math.pow(10, i); j > 0; j--) {
                x = Math.random();
                y = Math.random();
                if (Math.hypot(x-0.5d, y-0.5d) > 0.5){
                    summaValjas++;
                } else {
                    summaSees++;
                }
            }
            tulemus += Integer.toString((int)Math.pow(10, i)) + " katsega: " + Double.toString((double)summaSees/summaValjas) + "\n";
        }
        return tulemus;
    }
}
