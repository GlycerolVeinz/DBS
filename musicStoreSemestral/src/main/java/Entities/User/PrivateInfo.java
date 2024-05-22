package Entities.User;

import jakarta.persistence.*;

@Entity
@Table(name = "privateinfo")
public class PrivateInfo {
    @EmbeddedId
    private PrivateinfoId id;

    @MapsId("id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User users;

    @Column(name = "homenumber", length = 50)
    private String homenumber;

    @Column(name = "password", length = 50)
    private String password;

    public PrivateinfoId getId() {
        return id;
    }

    public void setId(PrivateinfoId id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getHomenumber() {
        return homenumber;
    }

    public void setHomenumber(String homenumber) {
        this.homenumber = homenumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}