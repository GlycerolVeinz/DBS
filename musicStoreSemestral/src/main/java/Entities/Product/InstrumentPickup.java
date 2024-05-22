package Entities.Product;

import jakarta.persistence.*;

@Entity
@Table(name = "instrumentpickup")
public class InstrumentPickup {
    @Id
    @Column(name = "installedonid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "instrument_isbn", referencedColumnName = "isbn"),
            @JoinColumn(name = "instrument_modelnumber", referencedColumnName = "modelnumber")
    })
    private InstrumentProduct instrumentProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "pickup_modelnumber", referencedColumnName = "modelnumber")
    })
    private Pickup pickup;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InstrumentProduct getInstrumentProduct() {
        return instrumentProduct;
    }

    public void setInstrumentProduct(InstrumentProduct instrumentProduct) {
        this.instrumentProduct = instrumentProduct;
    }

    public Pickup getPickup() {
        return pickup;
    }

    public void setPickup(Pickup pickup) {
        this.pickup = pickup;
    }
}
