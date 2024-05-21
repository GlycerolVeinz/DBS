package Entities.Product;

import Entities.Store.Store;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Product {
    @EmbeddedId
    private ProductId id;

    @Column(name = "productid", nullable = false)
    private Integer productid;

    @ManyToMany
    @JoinTable(
            name = "product_store",
            joinColumns = {@JoinColumn(name = "product_id")},
            inverseJoinColumns = {@JoinColumn(name = "store_id")}
    )
    private Set<Store> stores = new HashSet<>();

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    // Getters and Setters
    public ProductId getId() {
        return id;
    }

    public void setId(ProductId id) {
        this.id = id;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }
}
