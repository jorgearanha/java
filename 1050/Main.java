import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, String> map = new HashMap<>();
        map.put(61, "Brasilia");
        map.put(71, "Salvador");
        map.put(11, "Sao Paulo");
        map.put(21, "Rio de Janeiro");
        map.put(32, "Juiz de Fora");
        map.put(19, "Campinas");
        map.put(27, "Vitoria");
        map.put(31, "Belo Horizonte");

        int ddd = scanner.nextInt();
        String resposta = "DDD nao cadastrado";
        
        String destination = map.get(ddd);

        if (destination != null)
            resposta = map.get(ddd);

        System.out.print(resposta + "\n");

        scanner.close();
    }

}