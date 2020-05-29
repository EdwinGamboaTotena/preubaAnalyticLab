import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author EDWIN
 */
public class CountInsensitive {

    private static String capturarInput() throws IOException {
        System.out.print("Por favor ingrese el string a analizar: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputString = reader.readLine().toLowerCase();
        return inputString;
    }

    private static Map<Character, Integer> contarCaracteres(String inputString) {
        Map<Character, Integer> mapCaracteres = new HashMap<>();

        inputString.chars().mapToObj(c -> (char) c)
                .forEach(c -> mapCaracteres.compute(c, (k, v) -> v != null ? v + 1 : 1));

        mapCaracteres.entrySet().removeIf(entry -> (entry.getValue() < 2));
        return mapCaracteres;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String input = capturarInput();
            Map<Character, Integer> mapCaracteres = contarCaracteres(input);
            System.out.println(mapCaracteres);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
