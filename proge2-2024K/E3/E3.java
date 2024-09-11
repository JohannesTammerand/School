/**
 * Õpilase nimi: Johannes Tammerand
 * Programmeerimine 2 - Eksamiosa 3 06.06.2024.
 */
public class E3 {
    /**
     * Meetodi ülesandeks on kuavada ekraanile kõik antud sõne alamsõned, mille algus-ja lõpusümbol on samad.
     * Alamsõne peab olema pikkusega vähemalt 2
     * @param a Antud sõne
     */
    public static void samaAlgusLõpp(String a){
        for (int i = 0; i < a.length(); i++) {
            for (int j = a.length()-1; j > i; j--) {
                if (a.charAt(i) == a.charAt(j)){
                    System.out.println(a.substring(i, j+1));
                }
            }
        }
    }

    /**
     * Meetodi ülesaneks on antud sõnes kõigis suurtähtedest koosnevates lõikudes asendada kõik tähed peale esimese
     * väikeste tähtedega
     * @param s Antud sõne
     * @return Muudetud sõne
     */
    public static String asendaSuurtähti(String s){
        StringBuilder sb = new StringBuilder(s);

        boolean suur = false;
        for (int i = 0; i < sb.length(); i++) {
            if (Character.isUpperCase(sb.charAt(i))){
                if (suur){
                    sb.setCharAt(i, Character.toLowerCase(sb.charAt(i)));
                } else {
                    suur = true;
                }
            } else if (suur){
                suur = false;
            }
        }

        return sb.toString();
    }

    /**
     * Meetodi ülesandeks on leida antud sõnest osasõne, mis on allesjäänud sõnega võrdne
     * Ülesande teostamiseks kasutab rekursiivset abimeetodit osaSõneKordusRek
     * Kui nõutud osasõne ei leidu, tuleb tagastada väärtus null
     * @param str Antud sõne
     * @return Leitud osasõne või null
     */
    public static String osaSõneKordus(String str){
        StringBuilder sb = new StringBuilder(str);
        return osaSõneKordusRek(sb, "", 0);
    }

    /**
     * Rekursiivne abimeetod osaSõneKordus meetodile
     * @param str Sõne, millest osasõne võtta
     * @param substr Seni lleitud osasõne
     * @param start algusindeks algsõnes, kust osasõne järgmist tähte otsima hakata
     * @return Leitud sõne või null
     */
    public static String osaSõneKordusRek(StringBuilder str, String substr, int start){
        if (substr.length() < str.length()) { //Kui osasõne ei ole enam algsõnest lühem, pole siit võimalik nõutud osasõne leida
            for (int i = start; i < str.length(); i++) {
                char c = str.charAt(i);
                StringBuilder str2 = new StringBuilder(str);
                str2.deleteCharAt(i);

                if (str2.toString().equals(substr + c)) {
                    return substr + c;
                } else if (!(substr.length() > str.length())) {
                    String rek = osaSõneKordusRek(str2, substr + c, i);
                    if (rek != null) {
                        return rek;
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(osaSõneKordus("a"));
    }
}
