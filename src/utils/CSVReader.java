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

            // Første linje inneholder navnet på fondet
            String fundName = scanner.nextLine();

            String headerLine = null;

            // Finner linjen som inneholder kolonnenavnene
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.startsWith("Ticker,Name")) {
                    headerLine = line;
                    break;
                }
            }

            // Hvis vi ikke finner headeren, kan ikke CSV-filen leses
            if (headerLine == null) {
                throw new IllegalArgumentException(
                    "Fant ikke kolonneoverskriftene i CSV-filen."
                );
            }

            String[] headers =
                headerLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            // -1 betyr at kolonnen ikke er funnet enda
            int tickerIndex = -1;
            int nameIndex = -1;
            int sectorIndex = -1;
            int weightIndex = -1;
            int locationIndex = -1;

            // Finner plasseringen til kolonnene vi trenger
            for (int i = 0; i < headers.length; i++) {

                String header = headers[i]
                    .replace("\"", "")
                    .trim();

                switch (header) {
                    case "Ticker" ->
                        tickerIndex = i;

                    case "Name" ->
                        nameIndex = i;

                    case "Sector" ->
                        sectorIndex = i;

                    case "Weight (%)", "Market Weight" ->
                        weightIndex = i;

                    case "Location" ->
                        locationIndex = i;
                }
            }

            // Kontrollerer at alle nødvendige kolonner ble funnet
            if (tickerIndex == -1 ||
                nameIndex == -1 ||
                sectorIndex == -1 ||
                weightIndex == -1 ||
                locationIndex == -1) {

                throw new IllegalArgumentException(
                    "CSV-filen mangler en eller flere nødvendige kolonner."
                );
            }

            // Leser alle holdings
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if (line.isBlank()) {
                    continue;
                }

                String[] values =
                    line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String ticker =
                    values[tickerIndex].replace("\"", "");

                String name =
                    values[nameIndex].replace("\"", "");

                String sector =
                    values[sectorIndex].replace("\"", "");

                String weightValue =
                        values[weightIndex].replace("\"", "").trim();

                if (weightValue.equals("-")) {
                    continue;
                }

                double weight = Double.parseDouble(weightValue);

                String location =
                    values[locationIndex].replace("\"", "");

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