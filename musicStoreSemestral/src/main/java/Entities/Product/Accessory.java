package Entities.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "accessory")
public class Accessory {
    @Id
    @Column(name = "isbn", nullable = false, length = 50)
    private String isbn;

    @Column(name = "accessoryid", nullable = false)
    private Integer accessoryid;

    @Column(name = "type", length = 50)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "instrument_isbn", referencedColumnName = "isbn"),
            @JoinColumn(name = "instrument_modelnumber", referencedColumnName = "modelnumber")
    })
    private InstrumentProduct comeswith;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAccessoryid() {
        return accessoryid;
    }

    public void setAccessoryid(Integer accessoryid) {
        this.accessoryid = accessoryid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InstrumentProduct getComeswith() {
        return comeswith;
    }

    public void setComeswith(InstrumentProduct comeswith) {
        this.comeswith = comeswith;
    }

}