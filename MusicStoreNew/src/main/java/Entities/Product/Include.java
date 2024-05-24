package Entities.Product;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "includes")
public class Include {
    @EmbeddedId
    private IncludeId id;

    @MapsId("productid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productid", nullable = false)
    private Instrumentproduct productid;

    @MapsId("accessoryid")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "accessoryid", nullable = false)
    private Accessory accessoryid;

    public IncludeId getId() {
        return id;
    }

    public void setId(IncludeId id) {
        this.id = id;
    }

    public Instrumentproduct getProductid() {
        return productid;
    }

    public void setProductid(Instrumentproduct productid) {
        this.productid = productid;
    }

    public Accessory getAccessoryid() {
        return accessoryid;
    }

    public void setAccessoryid(Accessory accessoryid) {
        this.accessoryid = accessoryid;
    }

}