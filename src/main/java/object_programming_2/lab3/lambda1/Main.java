package object_programming_2.lab3.lambda1;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<String> getPricesInPLN(List<String> destinations, double xrate) {
        return ListCreator.collectFrom(destinations)
                .when(elem -> elem.startsWith("WAW"))
                .mapEvery(elem -> {
                    Scanner scanner = new Scanner(elem);
                    String from = scanner.next();
                    String destination = scanner.next();
                    int price = scanner.nextInt();
                    double priceInPln = price * xrate;
                    return String.format("to %s - price in PLN:  %.0f", destination, priceInPln);
                });
    }

    public static void main(String[] args) {
        // Lista destynacji: port_wylotu port_przylotu cena_EUR
        List<String> dest = Arrays.asList(
                "bleble bleble 2000",
                "WAW HAV 1200",
                "xxx yyy 789",
                "WAW DPS 2000",
                "WAW HKT 1000"
        );
        double ratePLNvsEUR = 4.30;
        List<String> result = getPricesInPLN(dest, ratePLNvsEUR);
        for (String r : result) System.out.println(r);
    }
}
