package algorithms;

import model.Fund;

public class OverlapAnalyzer {
    
    Fund fund1;
    Fund fund2;

    public OverlapAnalyzer(Fund fund1, Fund fund2) {
        this.fund1 = fund1;
        this.fund2 = fund2;
    }

    public void showOverlap() {

        System.out.println("\n" + "--- Overlapp av selskaper ---" + "\n");

        for (var holding1 : fund1.getHoldings()) {
            for (var holding2 : fund2.getHoldings()) {

                if (holding1.getTicker().equals(holding2.getTicker())) {

                    System.out.println("\n" + "Selskap: " + holding1.getName() + "\n");
                    System.out.println(
                        "Vektoverlapp: " + fund1.getName() + ": " + holding1.getWeight() + "%"
                    );
                    System.out.println(
                        "Vektoverlapp: " + fund2.getName() + ": " + holding2.getWeight() + "%"
                    );
                }
            }
        }

    }





    
}
