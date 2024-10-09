/***********************************
 * Algoritmid ja andmestruktuurid. LTAT.03.005
 * 2024/2025 kevadsemester
 *
 * Kodutöö nr 1
 * Teema: Sorteerimismeetodite ajalise keerukuse võrdlus
 *
 * Autor: Johannes Tammerand
 *
 **********************************/

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class KT1 {
    /**
     * Peameetod sorteerimismeetodite kasutamiseks
     * @param args
     */
    public static void main(String[] args) {
        try {
            Random rand = new Random();
            FileWriter output = new FileWriter("C:\\Users\\johan\\school\\AA2024S\\KT1\\output.txt");
            for (int i = 0; i <= 100000; i += 1000) {
                System.out.println(i);
                int[] a = new int[i];
                int[] b;
                for (int j = 0; j < i; j++) {
                    a[j] = rand.nextInt(); //Suvaline 32 bitine märgiga täisarv
                }
                long start = System.currentTimeMillis();
                b = valikumeetod(a);
                long stop = System.currentTimeMillis();
                if (!test(a)) {
                    System.out.println(Arrays.toString(a));
                    break;
                }
                output.write(i + " " + (stop - start) + "\n");
            }
            output.close();
        } catch (IOException e){
            System.out.println("oops");
        }
    }


    /**
     * Valikumeetodil järjendi sorteerimine mittekasvavasse järjestusse
     * @param a Sorteerimata järjend
     * @return Sorteeritud järjend
     */
    public static int[] valikumeetod(int[] a){
        for (int i = 0; i < a.length; i++) {
            int maxI = i; //Suurima indeksi element, mis asub peale indeksit i
            for (int j = i+1; j < a.length; j++) {
                if (a[j] >= a[maxI]){
                    maxI = j;
                }
            }
            int temp = a[maxI];
            a[maxI] = a[i];
            a[i] = temp;
        }
        return a;
    }

    /**
     * Järjendi sorteerimine shellimeetodiga mittekasvavasse järjestusse
     * Algoritm võetud lehelt: https://www.programiz.com/dsa/shell-sort
     * @param a Sorteerimata järjend
     * @return Sorteeritud järjend
     */
    public static int[] shellimeetod(int[] a){
        int n = a.length;
        for (int interval = n/2; interval > 0; interval /= 2){
            for (int i = interval; i < n; i++){
                int temp = a[i];
                int j;
                for (j = i; j >= interval && a[j - interval] <= temp; j -= interval) {
                    a[j] = a[j-interval];
                }
                a[j] = temp;
            }
        }
        return a;
    }

    /**
     * Funktsioon järjendi ümberpööramiseks
     * Vajalik, kuna süsteemi sorteerimismeetod paneb arvud kasvavasse järjekorda
     * @param a Järjend
     * @return Ümberpööratud järjend
     */
    public static int[] reverse(int[] a){
        for (int i = 0; i < a.length/2; i++) {
            int temp = a[i];
            a[i] = a[a.length-1-i];
            a[a.length-1-i] = temp;
        }
        return a;
    }

    /**
     * Funktsioon, millega testida, kas järjend on mittekasvavas järjestuses
     * @param a Järjend
     * @return Tõeväärtus
     */
    public static boolean test(int[] a){
        for (int i = 1; i < a.length; i++){
            if (a[i] > a[i-1]){
                return false;
            }
        }
        return true;
    }
}
