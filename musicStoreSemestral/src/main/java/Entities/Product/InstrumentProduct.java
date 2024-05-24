package Entities.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "instrumentproduct")
public class InstrumentProduct extends Product {
    @Column(name = "range", length = 50)
    private String range;

    @Column(name = "type", length = 50)
    private String type;

    // Getters and Setters
    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
