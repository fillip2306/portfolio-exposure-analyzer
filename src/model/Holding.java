package model;

public class Holding {
    
    private String ticker;
    private String name;
    private String sector;
    private double weight;
    private String location;


    public Holding(String ticker, String name, String sector, double weight, String location) {
        this.ticker = ticker;
        this.name = name;
        this.sector = sector;
        this.weight = weight;
        this.location = location;
    }

    public String toString() {
        return "Holding{" +
                "ticker=" + ticker +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", weight=" + weight +
                ", location='" + location + '\'' +
                '}';
    }

    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return name;
    }
    public String getSector() {
        return sector;
    }
    public double getWeight() {
        return weight;
    }
    public String getLocation() {
        return location;
    }


}
