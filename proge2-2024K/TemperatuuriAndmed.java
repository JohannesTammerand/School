import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Abiklass failist temperatuuriandmete lugemiseks
 */
public class TemperatuuriAndmed {

    // Kolm massiivi, mis täidetakse failist loetud andmetega
    String[] kuupäev;
    String[] kellaaeg;
    double[] temperatuur;

    public TemperatuuriAndmed(String[] kuupäev, String[] kellaaeg, double[] temperatuur) {
        this.kuupäev = kuupäev;
        this.kellaaeg = kellaaeg;
        this.temperatuur = temperatuur;
    }

    /**
     * Loeb ja tagastab andmed failist nimega {@code failitee}
     */
    public static TemperatuuriAndmed loeAndmed(String failitee) {
        try {
            // Loeme failist kõik read, eeldame et faili kodeering on UTF-8
            List<String> read = Files.readAllLines(Path.of(failitee), StandardCharsets.UTF_8);

            // Määrame massiivide pikkuse
            String[] kuupäev = new String[read.size()];
            String[] kellaaeg = new String[read.size()];
            double[] temperatuur = new double[read.size()];
            for (int i = 0; i < read.size(); i++) {
                // Hakime read tühikute põhjal
                String[] jupid = read.get(i).split(" ");
                // Määrame hakitud jupid vastavatesse massiividesse
                kuupäev[i] = jupid[0];
                kellaaeg[i] = jupid[1];
                temperatuur[i] = Double.parseDouble(jupid[2]);
            }
            return new TemperatuuriAndmed(kuupäev, kellaaeg, temperatuur);
        } catch (IOException e) {
            // Faili ei leitud või lugemisel esines viga - viskame erindi ja lõpetame töö
            throw new UncheckedIOException("Faili " + failitee + " lugemisel tekkis viga", e);
        }
    }

}
