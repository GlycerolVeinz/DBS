package Entities.Product;

import Entities.Store.Store;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @ColumnDefault("nextval('product_productid_seq'::regclass)")
    @Column(name = "productid", nullable = false)
    private Integer id;

    @Column(name = "isbn", nullable = false, length = 50)
    private String isbn;

    @Column(name = "modelnumber", nullable = false, length = 50)
    private String modelnumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "location", referencedColumnName = "location")
    private Store location;

    @Column(name = "price")
    private Integer price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getModelnumber() {
        return modelnumber;
    }

    public void setModelnumber(String modelnumber) {
        this.modelnumber = modelnumber;
    }

    public Store getLocation() {
        return location;
    }

    public void setLocation(Store location) {
        this.location = location;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}