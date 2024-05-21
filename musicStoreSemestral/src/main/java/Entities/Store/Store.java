package Entities.Store;

import Entities.Product.Product;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @Column(name = "location", nullable = false, length = 50)
    private String location;

    @Column(name = "storeid", nullable = false)
    private Integer storeid;

    @ManyToMany(mappedBy = "stores")
    private Set<Product> products = new HashSet<>();

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getStoreid() {
        return storeid;
    }

    public void setStoreid(Integer storeid) {
        this.storeid = storeid;
    }

}