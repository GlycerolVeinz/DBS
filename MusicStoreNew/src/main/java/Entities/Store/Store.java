package Entities.Store;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @ColumnDefault("nextval('store_storeid_seq'::regclass)")
    @Column(name = "storeid", nullable = false)
    private Integer id;

    @Column(name = "location", nullable = false, length = 50)
    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}