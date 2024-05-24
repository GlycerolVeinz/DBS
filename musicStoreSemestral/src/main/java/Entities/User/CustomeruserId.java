package Entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CustomeruserId implements Serializable {
    @Serial
    private static final long serialVersionUID = 9047428352641404894L;
    @Column(name = "usernickname", nullable = false, length = 50)
    private String usernickname;

    @Column(name = "usermail", nullable = false, length = 50)
    private String usermail;

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomeruserId entity = (CustomeruserId) o;
        return Objects.equals(this.usermail, entity.usermail) &&
                Objects.equals(this.usernickname, entity.usernickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usermail, usernickname);
    }

}