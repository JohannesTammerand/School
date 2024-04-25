import java.util.Random;

class Kiri extends Saadetis{
    private String uksekood;
    private int prioriteet;
    Kiri(String uksekood, int prioriteet){
        super(uksekood);
        this.uksekood = uksekood;
        this.prioriteet = prioriteet;
    }

    public int kohaletoimetamiseAeg() {
        Random rand = new Random();
        return prioriteet + rand.nextInt(2);
    }

    public String toString(){
        return "Saadetise tüüp: kiri, " + super.toString() + ", prioriteet: " + prioriteet;
    }
}