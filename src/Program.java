import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Holding;
import utils.CSVReader;

public class Program {

    private final CSVReader csvReader;
    private List<Holding> holdings;

    public Program() {
        this.csvReader = new CSVReader();
        this.holdings = new ArrayList<>();
    }

    public void Run() {

        Scanner scanner = new Scanner(System.in);

        int choice = 0;

        while (choice != 3) {
            System.out.println("\n--- Portfolio Exposure Analyzer ---");
            System.out.println("1: Importer fond fra CSV");
            System.out.println("2: Vis importerte holdings");
            System.out.println("3: Avslutt");

            System.out.print("Velg: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> importHoldings();
                case 2 -> showHoldings();
                case 3 -> System.out.println("Avslutter program");
                default -> System.out.println("Ugyldig valg.");
            }
        }

        scanner.close();
    }

    private void importHoldings() {
        try {
            holdings = csvReader.retrieveHoldings("data/IVV_holdings.csv");

            System.out.println(
                    "Fond importert. Antall holdings: " + holdings.size()
            );

        } catch (Exception e) {
            System.out.println(
                    "Kunne ikke lese holdings: " + e.getMessage()
            );
        }
    }

    private void showHoldings() {
        for (Holding holding : holdings) {
            System.out.println(holding);
        }
    }
}