package Entities.Product;

import Entities.User.CustomerUser;

import jakarta.persistence.*;

@Entity
@Table(name = "buys")
public class Buy {
    @Id
    @Column(name = "purchaseid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "isbn", referencedColumnName = "isbn"),
            @JoinColumn(name = "modelnumber", referencedColumnName = "modelnumber")
    })
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "usernickname", referencedColumnName = "usernickname"),
            @JoinColumn(name = "usermail", referencedColumnName = "usermail")
    })
    private CustomerUser customeruser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CustomerUser getCustomeruser() {
        return customeruser;
    }

    public void setCustomeruser(CustomerUser customeruser) {
        this.customeruser = customeruser;
    }

}