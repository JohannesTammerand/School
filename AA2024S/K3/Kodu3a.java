import java.io.File;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

/***********************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2024/2025 kevadsemester
 *
 * Kodutöö nr 3a
 * Teema: Magasin ja järjekord
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

class Kodu3a{

    /**
     * Meetodi ülesandeks on väljastada antud kausta sisu tasemeti järjekorda kasutades
     * @param tee Antud kausta tee
     */
    public static void failipuu(String tee){
        //Kaks järjekorda hoiavad infot failide kohta,m ida peabb veel läbi uurima
        Queue<File> kaustad = new ArrayDeque<>();
        Queue<Integer> tasemed = new ArrayDeque<>();
        kaustad.add(new File(tee));
        tasemed.add(0);

        while (!kaustad.isEmpty()){
            File f = kaustad.remove();
            int t = tasemed.remove();
            if (f.isDirectory()){
                System.out.println("\t".repeat(t) + '[' + f.getName() + ']');

                Queue<File> sisu = new ArrayDeque<>(Arrays.asList(f.listFiles()));
                int suurus = sisu.size();
                for (int i = 0; i < suurus; i++) {
                    kaustad.add(sisu.remove());
                    tasemed.add(t + 1);
                }
            } else {
                double suurus = f.length()/1024.0;
                suurus = Math.round(suurus*100)/100.0; //Ümardab kahe komakohani
                if (suurus <= 500){
                    System.out.println("\t".repeat(t) + f.getName() + " (" + suurus + " KB)");
                }
            }
        }
    }

    public static void main(String[] args) {
        failipuu(".\\Kodutöö");
    }//peameetod

}//Kodu3a