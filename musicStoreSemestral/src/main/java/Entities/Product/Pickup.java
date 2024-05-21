package Entities.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "pickup")
public class Pickup {
    @Id
    @Column(name = "modelnumber", nullable = false, length = 50)
    private String modelnumber;

    @Column(name = "pickupid", nullable = false)
    private Integer pickupid;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    public String getModelnumber() {
        return modelnumber;
    }

    public void setModelnumber(String modelnumber) {
        this.modelnumber = modelnumber;
    }

    public Integer getPickupid() {
        return pickupid;
    }

    public void setPickupid(Integer pickupid) {
        this.pickupid = pickupid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}