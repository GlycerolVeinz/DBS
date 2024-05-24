package Entities.User;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "customeruser")
@PrimaryKeyJoinColumn(name = "userid")
public class Customeruser extends User {
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userid", nullable = false)
    private User users;

    @Column(name = "cookies")
    private Integer cookies;

    @Column(name = "premiumstatus")
    private Boolean premiumstatus;

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
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

    @Override
    public String toString() {
        return "Customeruser{" + "id: " + super.getId() +
                "users=" + users +
                ", cookies=" + cookies +
                ", premiumstatus=" + premiumstatus +
                '}';
    }
}