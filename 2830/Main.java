import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int kung = Math.round(scanner.nextInt()/2.0f);
        int lu = Math.round(scanner.nextInt()/2.0f);
        int cont = 0;

        while (kung != lu) {
            kung = Math.round(kung/2.0f);
            lu = Math.round(lu/2.0f);
            cont++;
        }

        Map<Integer, String> fase = new HashMap<>();
        fase.put(0, "oitavas");
        fase.put(1, "quartas");
        fase.put(2, "semifinal");
        fase.put(3, "final");

        System.out.println(fase.get(cont));

        scanner.close();
    }

}