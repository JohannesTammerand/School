abstract class Saadetis implements Comparable<Saadetis>{
    private String uksekood;
    Saadetis(String uksekood){
        this.uksekood = uksekood;
    }

    public String getUksekood(){
        return uksekood;
    }

    public void setUksekood(String uusKood){
        this.uksekood = uusKood;
    }

    public abstract int kohaletoimetamiseAeg();

    public String toString(){
        return "uksekood: " + uksekood;
    }

    public int compareTo(Saadetis teine){
        return this.kohaletoimetamiseAeg() - teine.kohaletoimetamiseAeg();
    }
}