package Entities.Product;

import javax.persistence.*;

@Entity
@Table(name = "instrumentpickup")
public class Instrumentpickup {
    @Id
    @Column(name = "installedonid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instrumentid", nullable = false, referencedColumnName = "instrumentid")
    private InstrumentProduct instrumentid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickupid", referencedColumnName = "pickupid")
    private Pickup pickupid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "modelnumber", nullable = false)
    private InstrumentProduct modelnumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pickupmodelnumber")
    private Pickup pickupmodelnumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InstrumentProduct getInstrumentid() {
        return instrumentid;
    }

    public void setInstrumentid(InstrumentProduct instrumentid) {
        this.instrumentid = instrumentid;
    }

    public Pickup getPickupid() {
        return pickupid;
    }

    public void setPickupid(Pickup pickupid) {
        this.pickupid = pickupid;
    }

    public InstrumentProduct getModelnumber() {
        return modelnumber;
    }

    public void setModelnumber(InstrumentProduct modelnumber) {
        this.modelnumber = modelnumber;
    }

    public Pickup getPickupmodelnumber() {
        return pickupmodelnumber;
    }

    public void setPickupmodelnumber(Pickup pickupmodelnumber) {
        this.pickupmodelnumber = pickupmodelnumber;
    }

}