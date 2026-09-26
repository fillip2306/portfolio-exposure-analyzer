import java.util.Scanner;

public class Program {
    
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
                case 1 -> System.out.println("Importer fond");
                case 2 -> System.out.println("Vis holdings");
                case 3 -> System.out.println("Avslutter program");
                default -> System.out.println("Ugyldig valg.");
            }
        }

        scanner.close();
    }
}
        
