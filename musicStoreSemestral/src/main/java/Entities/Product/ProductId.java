package Entities.Product;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductId implements Serializable {
    @Serial
    private static final long serialVersionUID = -7065411362355070039L;
    @Column(name = "isbn", nullable = false, length = 50)
    private String isbn;

    @Column(name = "modelnumber", nullable = false, length = 50)
    private String modelnumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId entity = (ProductId) o;
        return Objects.equals(this.modelnumber, entity.modelnumber) &&
                Objects.equals(this.isbn, entity.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelnumber, isbn);
    }

}