package model;

import java.util.UUID;
import java.util.List;

public class Fund {

    private UUID id;
    private String name;
    private List<Holding> holdings;

    public Fund(String name, List<Holding> holdings) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.holdings = holdings;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Holding> getHoldings() {
        return holdings;
    }

    @Override 
    public String toString() {
        return "Fund{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", holdings=" + holdings +
                '}';
    }
    
}
