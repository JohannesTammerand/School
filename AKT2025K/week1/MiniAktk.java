package week1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

public class MiniAktk {
    public static void main(String[] args) {
        try {
            File input = new File(args[0]);
            Scanner reader = new Scanner(input);

            TreeMap<Character, Integer> vars = new TreeMap<>();

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line == "" || line.charAt(0) == '#'){
                    continue;
                }

                line = line.split("#")[0];
                String[] parts = line.split(" ");
                if (Objects.equals(parts[0], "print")){
                    System.out.println(avaldis(vars, line.substring(5)));
                } else {
                    char var = parts[0].charAt(0);
                    vars.put(var, avaldis(vars, line.split("=")[1]));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static int avaldis(TreeMap<Character, Integer> vars, String avaldis){
        avaldis = avaldis.strip();
        try {
            int val = Integer.parseInt(avaldis);
            return val;
        } catch (NumberFormatException e){
            if (avaldis.length() == 1){
                return vars.get(avaldis.charAt(0));
            }

            String[] parts = avaldis.split("\\+");
            int val = 0;
            if (parts.length > 1){
                for (String s : parts){
                    val += avaldis(vars, s);
                }
            } else if (parts.length == 1){
                parts = avaldis.split("-");
                val = avaldis(vars, parts[0]);
                for (int i = 1; i < parts.length; i++){
                    String s = parts[i];
                    val -= avaldis(vars, s);
                }
            }

            return val;
        }
    }
}
