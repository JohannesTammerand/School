import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

class Pakiautomaat{
    private int kappideArv;
    private List<Saadetis> saadetised = new ArrayList<Saadetis>();

    Pakiautomaat(int kappideArv){
        this.kappideArv = kappideArv;
    }

    public void lisaPakiautomaati(Saadetis saadetis){
        if (saadetised.size() < kappideArv){
            if (Objects.equals(saadetis.getUksekood(), "")){
                Random rand = new Random();
                saadetis.setUksekood(Integer.toString(rand.nextInt(1000, 10000)));
            }
            saadetised.add(saadetis);
        } else {
            System.out.println("Kapis vabu kohti pole");
        }
    }

    public Saadetis eemaldaPakiutomaadist(String uksekood){
        for (Saadetis saadetis : saadetised){
            if (Objects.equals(saadetis.getUksekood(), uksekood)){
                this.saadetised.remove(saadetis);
                return saadetis;
            }
        }
        return null;
    }

    public String toString(){
        String tulemus = "Pakiautomaais olevad saadetised:\n";
        for (Saadetis saadetis : saadetised){
            tulemus += saadetis.toString() + "\n";
        }
        return tulemus;
    }
}