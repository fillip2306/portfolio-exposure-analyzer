package utils;

import model.Holding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public List<Holding> retrieveHoldings(String filePath) throws Exception {

        List<Holding> allHoldings = new ArrayList<>();

        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {

            // Hopper over informasjonen øverst i CSV-filen
            for (int i = 0; i < 10; i++) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                // Hopper over tomme linjer
                if (line.isBlank()) {
                    continue;
                }

                String[] values =
                        line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                String ticker = values[0].replace("\"", "");
                String name = values[1].replace("\"", "");
                String sector = values[2].replace("\"", "");
                double weight = Double.parseDouble(values[5].replace("\"", ""));
                String location = values[9].replace("\"", "");

                Holding holding = new Holding(
                        ticker,
                        name,
                        sector,
                        weight,
                        location
                );

                allHoldings.add(holding);
            }
        }

        return allHoldings;
    }
}