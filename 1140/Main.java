import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(final String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<String> frases = new ArrayList<String>();

        String linha = scanner.nextLine();

        while (!linha.equals("*")) {
            frases.add(linha);
            linha = scanner.nextLine();
        }

        for (String frase : frases) {
            String[] palavras = frase.split(" ");
            isTaut(palavras);
        }

        scanner.close();

    }

    public static void isTaut(String[] palavras) {

        boolean taut = true;
        char letra = palavras[0].toUpperCase().charAt(0);

        for (int i = 1; i < palavras.length; i++) {
            if (palavras[i].toUpperCase().charAt(0) != letra) 
                taut = false;
        }

        if (taut)
            System.out.println("Y");
        else
            System.out.println("N");

    }

}
