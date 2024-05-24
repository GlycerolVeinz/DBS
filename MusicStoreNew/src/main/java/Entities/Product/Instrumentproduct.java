package Entities.Product;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "instrumentproduct")
@PrimaryKeyJoinColumn(name = "productid")
public class Instrumentproduct {
    @Id
    @Column(name = "productid", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productid", nullable = false)
    private Product product;

    @Column(name = "range", length = 50)
    private String range;

    @Column(name = "type", length = 50)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Instrumentproduct{" +
                "id=" + id +
                ", product=" + product +
                ", range='" + range + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}