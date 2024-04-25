import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class Peaklass {

    public static List<Saadetis> loeSaadetised(String failinimi){
        List<Saadetis> saadetised = new ArrayList<Saadetis>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(failinimi));
            String line = reader.readLine();
            String uksekood = "";
            while (line != null){
                uksekood = "";
                String[] saadetisList = line.split(";");
                if (saadetisList.length == 3) {
                    uksekood = saadetisList[2];
                }
                if (Objects.equals(saadetisList[0], "kiri")){
                    saadetised.add(new Kiri(uksekood, Integer.valueOf(saadetisList[1])));
                } else {
                    saadetised.add(new Pakk(uksekood, Double.valueOf(saadetisList[1])));
                }
                line = reader.readLine();
            }
            reader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Faili ei leitud");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return saadetised;
    }
    public static void main(String[] args) {
        List<Saadetis> saadetised = loeSaadetised("saadetised.txt");
        Collections.sort(saadetised);
        Pakiautomaat pakiautomaat = new Pakiautomaat(5);
        for(Saadetis saadetis : saadetised){
            pakiautomaat.lisaPakiautomaati(saadetis);
        }
        System.out.println(pakiautomaat.toString());
        System.out.println("Eemaldatud saadetis:\n" + pakiautomaat.eemaldaPakiutomaadist("1234").toString() + "\n");
        System.out.println(pakiautomaat.toString());
    }
}
