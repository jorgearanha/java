import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(final String[] args) {

        Scanner scanner = new Scanner(System.in);
        
        int entrada = scanner.nextInt();
        int fatorial = 1;

        for (int i = entrada; 1 < i; i--) {
            fatorial = i * fatorial;
        }

        System.out.println(fatorial);

        scanner.close();

    }

}
