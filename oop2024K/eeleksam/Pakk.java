class Pakk extends Saadetis{
    private String uksekood;
    private double kaal;
    Pakk(String uksekood, double kaal){
        super(uksekood);
        this.uksekood = uksekood;
        this.kaal = kaal;
    }

    public int kohaletoimetamiseAeg(){
        return ((int)kaal)*2;
    }

    public String toString(){
        return "Saadetise tüüp: pakk, " + super.toString() + ", kaal: " + kaal;
    }
}