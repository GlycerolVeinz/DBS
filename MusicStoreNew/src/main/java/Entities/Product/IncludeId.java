package Entities.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IncludeId implements Serializable {
    private static final long serialVersionUID = 1248446601425078427L;
    @Column(name = "productid", nullable = false)
    private Integer productid;

    @Column(name = "accessoryid", nullable = false)
    private Integer accessoryid;

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getAccessoryid() {
        return accessoryid;
    }

    public void setAccessoryid(Integer accessoryid) {
        this.accessoryid = accessoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IncludeId entity = (IncludeId) o;
        return Objects.equals(this.productid, entity.productid) &&
                Objects.equals(this.accessoryid, entity.accessoryid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productid, accessoryid);
    }

}