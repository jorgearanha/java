import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);

        int i = scanner.nextInt();
        scanner.nextLine();

        List<String> frases = new ArrayList<String>();

        for (int j = 0; j < i; j++) {
            frases.add(scanner.nextLine());
        }

        for (String frase : frases) {
            printMensOculta(frase);
        }

        scanner.close();
    }

    public static void printMensOculta(String enigma){
        String mensagem = enigma.replaceAll("\\B(?:[a-z])\\w*|\\s+", "");
        System.out.println(mensagem);
    }

}

