import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Õpilase nimi: Johannes Tammerand
 * Programmeerimine 2 - KT 3 29.05.2024.
 * UnsupportedOperationException tähistab, et meetod on realiseerimata. Kirjuta selle asemele oma lahenduse kood.
 * Palume mitte "package" märksõna lisada, mallis olevate meetodite signatuure muuta, ega (ka realiseerimata) meetodeid kustutada!
 */
public class Kt3 {

    public static String sidekriipsud(String s) {
        if (s.length() == 0){
            return s;
        }



        StringBuilder sb = new StringBuilder(s);
        char eelmine = Character.toLowerCase(sb.charAt(0));

        boolean kordus = false;
        for(int i = 1; i < sb.length(); i++){
            if (Character.toLowerCase(sb.charAt(i)) == eelmine){
                if (kordus){
                    sb.insert(i, '-');
                    i++;
                    kordus = false;
                } else {
                    kordus = true;
                }
            } else {
                kordus = false;
                eelmine = Character.toLowerCase(sb.charAt(i));
            }
        }

        return sb.toString();
    }

    public static String[] pikimÜhissufiks(String s) {
        String[] margid = {".", ",", ";", ":", "!", "?"};
        int margidI = 0;
        while (margidI < 5){
            if (s.contains(margid[margidI])){
                s = s.replace(margid[margidI], "");
            } else {
                margidI++;
            }
        }

        String[] sA = s.split(" ");

        if (sA.length < 2){
            return null;
        }

        String[] tulemus = new String[2];
        int suurim = 0;
        for (int i = 0; i < sA.length; i++){
            for (int j = i+1; j < sA.length; j++){
                String a = sA[i];
                String b = sA[j];
                int sufiks = pikimÜhissufiksAbi(a, b);
                if (sufiks > suurim){
                    suurim = sufiks;
                    tulemus[0] = a;
                    tulemus[1] = b;
                }
            }
        }

        if (tulemus[0] == null){
            return null;
        } else {
            return tulemus;
        }
    }

    public static int pikimÜhissufiksAbi(String s1, String s2){
        int pikkus = Math.min(s1.length(), s2.length());
        int tulemus = 0;
        for(int i = 1; i <= pikkus; i++){
            if (s1.charAt(s1.length() - i) == s2.charAt(s2.length() - i)){
                tulemus++;
            } else {
                break;
            }
        }

        return tulemus;
    }

    public static String sulgevadSulud(String s) {
        ArrayList<Integer> avanevad = new ArrayList<>();
        ArrayList<Integer> sulguvad = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                avanevad.add(i);
            } else if (s.charAt(i) == ')'){
                sulguvad.add(i);
            }
        }

        for(int a : sulguvad){
            for (int i = 0; i < avanevad.size(); i++){

            }
        }

        throw new UnsupportedOperationException();
    }

    public static ArrayList<Integer> sorteeri(ArrayList<Integer> a, ArrayList<Integer> b, int n){
        int vaikseim = a.get(0);
        int vaikseimIndex = 0;
        for (int i = 0; i < a.size(); i++){
            if (a.get(i) < vaikseim){
                vaikseim = a.get(i);
                vaikseimIndex = i;
            }
        }

        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        //System.out.println(sidekriipsud("Aaaaarghhh"));

        String a = "koko! koko";
        String[] b = pikimÜhissufiks(a);
        if (b != null) {
            for (String ab : b) {
                System.out.println(ab);
            }
        } else {
            System.out.println("HEhe");
        }
    }
}