package Entities.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "instrumentproduct")
@AttributeOverrides({
        @AttributeOverride(name = "id.isbn", column = @Column(name = "isbn")),
        @AttributeOverride(name = "id.modelnumber", column = @Column(name = "modelnumber")),
})
public class InstrumentProduct extends Product {
    @Column(name = "instrumentid", nullable = false)
    private Integer instrumentid;

    @Column(name = "range", length = 50)
    private String range;

    @Column(name = "type", length = 50)
    private String type;

    // Getters and Setters
    public Integer getInstrumentid() {
        return instrumentid;
    }

    public void setInstrumentid(Integer instrumentid) {
        this.instrumentid = instrumentid;
    }

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
