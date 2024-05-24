package Entities.Product;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pasystemproduct")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pasystemproduct extends Product{
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    @Column(name = "range", length = 50)
    private String range;

    @Column(name = "type", length = 50)
    private String type;

    @Column(name = "resistance", length = 50)
    private String resistance;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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