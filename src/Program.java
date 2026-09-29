import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
            System.out.println("3: Avslutt" + "\n");

            System.out.print("Velg: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> importFund(scanner);
                case 2 -> showHoldings();
                case 3 -> System.out.println("Avslutter program");
                default -> System.out.println("Ugyldig valg.");
            }
        }

        scanner.close();
    }

    private void importFund(Scanner scanner) {

        try {

            List<String> files = listFiles("data");

            System.out.println("\n" + "Velg CSV-filen du vil importere:");

            for (int i = 0; i < files.size(); i++) {
                System.out.println((i + 1) + ": " + files.get(i));
            }

            System.out.print("Velg: ");
            int fileChoice = scanner.nextInt();
            if (fileChoice < 1 || fileChoice > files.size()) {
                System.out.println("Ugyldig valg.");
                return;
            }

            String selectedFile = files.get(fileChoice - 1);

            Fund fund = csvReader.retrieveFund("data/" + selectedFile);

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

    private List<String> listFiles(String directoryPath) {
        try (Stream<Path> stream = Files.list(Paths.get(directoryPath))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .sorted()
                    .toList();
        } catch (IOException e) {
            System.err.println("Error reading directory: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}