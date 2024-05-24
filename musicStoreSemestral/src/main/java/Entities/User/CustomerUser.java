package Entities.User;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "customeruser")
public class CustomerUser {
    @EmbeddedId
    private CustomeruserId id;

    @MapsId("id")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "usernickname", referencedColumnName = "nickname", nullable = false),
            @JoinColumn(name = "usermail", referencedColumnName = "email", nullable = false)
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User users;

    @ColumnDefault("nextval('customeruser_customerid_seq'::regclass)")
    @Column(name = "customerid", nullable = false)
    private Integer customerid;

    @Column(name = "cookies")
    private Integer cookies;

    @Column(name = "premiumstatus")
    private Boolean premiumstatus;

    public CustomeruserId getId() {
        return id;
    }

    public void setId(CustomeruserId id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Integer getCookies() {
        return cookies;
    }

    public void setCookies(Integer cookies) {
        this.cookies = cookies;
    }

    public Boolean getPremiumstatus() {
        return premiumstatus;
    }

    public void setPremiumstatus(Boolean premiumstatus) {
        this.premiumstatus = premiumstatus;
    }

}