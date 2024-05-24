package Entities.Product;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "accessory")
public class Accessory {
    @Id
    @ColumnDefault("nextval('accessory_accessoryid_seq'::regclass)")
    @Column(name = "accessoryid", nullable = false)
    private Integer id;

    @Column(name = "type", length = 50)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comeswith")
    private Instrumentproduct comeswith;

    @Column(name = "isbn", length = 50)
    private String isbn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instrumentproduct getComeswith() {
        return comeswith;
    }

    public void setComeswith(Instrumentproduct comeswith) {
        this.comeswith = comeswith;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}