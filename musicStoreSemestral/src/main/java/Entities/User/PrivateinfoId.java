package Entities.User;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrivateinfoId implements Serializable {
    @Serial
    private static final long serialVersionUID = 1820287446345463706L;
    @Column(name = "usernickname", nullable = false, length = 50)
    private String usernickname;

    @Column(name = "usermail", nullable = false, length = 50)
    private String usermail;

    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;

    @Column(name = "street", nullable = false, length = 50)
    private String street;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "zip", nullable = false, length = 50)
    private String zip;

    @Column(name = "phonenumber", nullable = false, length = 12)
    private String phonenumber;

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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivateinfoId entity = (PrivateinfoId) o;
        return Objects.equals(this.zip, entity.zip) &&
                Objects.equals(this.city, entity.city) &&
                Objects.equals(this.street, entity.street) &&
                Objects.equals(this.phonenumber, entity.phonenumber) &&
                Objects.equals(this.usermail, entity.usermail) &&
                Objects.equals(this.fullname, entity.fullname) &&
                Objects.equals(this.usernickname, entity.usernickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zip, city, street, phonenumber, usermail, fullname, usernickname);
    }

}