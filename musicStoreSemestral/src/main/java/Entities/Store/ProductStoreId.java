package Entities.Store;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductStoreId implements Serializable {
    @Serial
    private static final long serialVersionUID = -6829182841695619516L;
    @Column(name = "product_isbn", nullable = false, length = 50)
    private String productIsbn;

    @Column(name = "product_modelnumber", nullable = false, length = 50)
    private String productModelnumber;

    @Column(name = "store_id", nullable = false, length = 50)
    private String storeId;

    public String getProductIsbn() {
        return productIsbn;
    }

    public void setProductIsbn(String productIsbn) {
        this.productIsbn = productIsbn;
    }

    public String getProductModelnumber() {
        return productModelnumber;
    }

    public void setProductModelnumber(String productModelnumber) {
        this.productModelnumber = productModelnumber;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductStoreId entity = (ProductStoreId) o;
        return Objects.equals(this.productModelnumber, entity.productModelnumber) &&
                Objects.equals(this.productIsbn, entity.productIsbn) &&
                Objects.equals(this.storeId, entity.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productModelnumber, productIsbn, storeId);
    }

}