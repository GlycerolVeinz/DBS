package Entities.Product;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "instrumentpickup")
public class Instrumentpickup {
    @Id
    @ColumnDefault("nextval('instrumentpickup_installedonid_seq'::regclass)")
    @Column(name = "installedonid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "instrumentid", nullable = false)
    private Instrumentproduct instrumentid;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "pickupid")
    private Pickup pickupid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instrumentproduct getInstrumentid() {
        return instrumentid;
    }

    public void setInstrumentid(Instrumentproduct instrumentid) {
        this.instrumentid = instrumentid;
    }

    public Pickup getPickupid() {
        return pickupid;
    }

    public void setPickupid(Pickup pickupid) {
        this.pickupid = pickupid;
    }

}