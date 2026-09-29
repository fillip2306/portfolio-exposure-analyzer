package utils;

import model.Holding;
import model.Fund;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CSVReader {

  public Fund retrieveFund(String filePath) throws Exception {

    List<Holding> allHoldings = new ArrayList<>();

    File file = new File(filePath);

    try (Scanner scanner = new Scanner(file)) {

        String fundName = scanner.nextLine();

        // Hopper over resten av informasjonen frem til holdings
        for (int i = 0; i < 9; i++) {
            scanner.nextLine();
        }

        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();

            if (line.isBlank()) {
                continue;
            }

            String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            String ticker = values[0].replace("\"", "");
            String name = values[1].replace("\"", "");
            String sector = values[2].replace("\"", "");
            double weight =
                    Double.parseDouble(values[5].replace("\"", ""));
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

        return new Fund(fundName, allHoldings);
    }
}
}