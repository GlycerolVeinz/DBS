package Entities.Product;

import Entities.User.Customeruser;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "buys")
public class Buy {
    @Id
    @ColumnDefault("nextval('buys_purchaseid_seq'::regclass)")
    @Column(name = "purchaseid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "productid", nullable = false)
    private Product productid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "customerid", nullable = false)
    private Customeruser customerid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProductid() {
        return productid;
    }

    public void setProductid(Product productid) {
        this.productid = productid;
    }

    public Customeruser getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Customeruser customerid) {
        this.customerid = customerid;
    }

}