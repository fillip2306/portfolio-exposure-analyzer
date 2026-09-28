import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Fund;
import model.Holding;
import utils.CSVReader;

public class Program {

    private final CSVReader csvReader;
    private List<Fund> funds;

    public Program() {
        this.csvReader = new CSVReader();
        this.funds = new ArrayList<>();
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
                case 1 -> importFund();
                case 2 -> showHoldings();
                case 3 -> System.out.println("Avslutter program");
                default -> System.out.println("Ugyldig valg.");
            }
        }

        scanner.close();
    }

    private void importFund() {

        try {
            Fund fund = csvReader.retrieveFund("data/IVV_holdings.csv");

            funds.add(fund);

            System.out.println(
                    "Fond importert: " + fund.getName() +
                    " (" + fund.getHoldings().size() + " holdings)"
            );

        } catch (Exception e) {
            System.out.println(
                    "Kunne ikke lese fond: " + e.getMessage()
            );
        }
    }

    private void showHoldings() {

        for (Fund fund : funds) {

            System.out.println("\nFond: " + fund.getName());

            for (Holding holding : fund.getHoldings()) {
                System.out.println(holding);
            }
        }
    }
}