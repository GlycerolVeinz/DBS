package Entities.Product;

import Entities.User.CustomerUser;

import javax.persistence.*;

@Entity
@Table(name = "buys")
public class Buy {
    @Id
    @Column(name = "purchaseid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
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