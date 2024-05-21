package Entities.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "pasystemproduct")
@AttributeOverrides({
        @AttributeOverride(name = "id.isbn", column = @Column(name = "isbn")),
        @AttributeOverride(name = "id.modelnumber", column = @Column(name = "modelnumber"))
})
public class PASystemProduct extends Product {
    @Column(name = "systemid", nullable = false)
    private Integer systemid;

    @Column(name = "range", length = 50)
    private String range;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "resistance", length = 50)
    private String resistance;

    // Getters and Setters
    public Integer getSystemid() {
        return systemid;
    }

    public void setSystemid(Integer systemid) {
        this.systemid = systemid;
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

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }
}
