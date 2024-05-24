package Entities.Store;

import jakarta.persistence.*;

@Entity
@Table(name = "product_store")
public class ProductStore {
    @EmbeddedId
    private ProductStoreId id;

    @MapsId("storeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    public ProductStoreId getId() {
        return id;
    }

    public void setId(ProductStoreId id) {
        this.id = id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

}