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
        List<Integer> numbers = new ArrayList<Integer>() {};

        for (int j = 0; j < i; j++) {
            numbers.add(scanner.nextInt());
        }

        for (Integer number : numbers) {
            if (number == 0)
                System.out.println("NULL");
            else
                System.out.println(evenOrOdd(number) + " " + posOrNeg(number));
        }

        scanner.close();
    }

    public static String evenOrOdd(Integer num) {
        if ((num % 2) == 0)
            return "EVEN";
        else
            return "ODD";
    }

    public static String posOrNeg(Integer num) {
        if ( num > 0 ) return "POSITIVE";
        else return "NEGATIVE";
    }

}

