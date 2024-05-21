package Entities.User;

import jakarta.persistence.*;

@Entity
@Table(name = "customeruser")
@AttributeOverrides({
        @AttributeOverride(name = "id.nickname", column = @Column(name = "customer_nickname")),
        @AttributeOverride(name = "id.email", column = @Column(name = "customer_email"))
})
public class CustomerUser extends User {
    @Column(name = "customerid", nullable = false)
    private Integer customerid;

    @Column(name = "cookies")
    private Integer cookies;

    @Column(name = "premiumstatus")
    private Boolean premiumstatus;

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
