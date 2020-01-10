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

        int i = scanner.nextInt();
        scanner.nextLine();

        List<String> textos = new ArrayList<>();
        List<String> chaves = new ArrayList<>();

        for (int j = 0; j < i; j++) {
            textos.add(scanner.nextLine());
            chaves.add(scanner.nextLine());
        }

        //System.out.println("---------------------------------------------------------------------------" + i);

        for (int j = 0; j < i; j++) {
            printNumMatches(chaves.get(j), textos.get(j));
        }

        scanner.close();
    }

    public static void printNumMatches(String chave, String texto) {

        String resultado = "";
        String regex = "(?<=^|\\s)" + chave + "(?=\\s|$)";

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
