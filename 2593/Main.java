import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Main
 */
public class Main {

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        String texto = scanner.nextLine();
        int i = scanner.nextInt();

        List<String> chaves = new ArrayList<>();

        for (int j = 0; j < i; j++) {
            chaves.add(scanner.next());
        }

        printNumMatches(chaves, texto);

        scanner.close();
    }

    public static void printNumMatches(List<String> chaves, String texto) {

        for (int i = 0; i < chaves.size(); i++) {

            String resultado = "";
            String regex = "(?<=^|\\s)" + chaves.get(i) + "(?=\\s|$)";

            Pattern r = Pattern.compile(regex);
            Matcher m = r.matcher(texto);

            while (m.find()) {
                if (!resultado.isEmpty())
                    resultado = resultado + " ";
                resultado = resultado + m.start(0);
            }
            
            if (resultado.isEmpty())
                resultado = resultado + "-1";

            System.out.println(resultado);
        }

    }

}
