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
            crescPop(frase);
        }

        scanner.close();
    }

    public static void crescPop(String dado) {

        String[] dados = dado.split(" ");

        int anos = 0;
        int pa = Integer.valueOf(dados[0]);
        int pb = Integer.valueOf(dados[1]);
        double g1 = Double.parseDouble(dados[2]);
        double g2 = Double.parseDouble(dados[3]);

        while (pa <= pb && anos <= 100) {
            pa = (int)(pa + (pa * (g1/100)));
            pb = (int)(pb + (pb * (g2/100)));
            anos++;
        }

        if (anos > 100)
            System.out.println("Mais de 1 seculo.");     
        else 
            System.out.println(anos + " anos.");
    
    }

}

