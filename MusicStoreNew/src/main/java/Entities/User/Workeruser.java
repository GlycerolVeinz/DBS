package Entities.User;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "workeruser")
@PrimaryKeyJoinColumn(name = "userid")
public class Workeruser extends User {
    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userid", nullable = false)
    private User users;

    @Column(name = "personalidentificationnumber", nullable = false, length = 50)
    private String personalidentificationnumber;

    @Column(name = "location", length = 50)
    private String location;


    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public String getPersonalidentificationnumber() {
        return personalidentificationnumber;
    }

    public void setPersonalidentificationnumber(String personalidentificationnumber) {
        this.personalidentificationnumber = personalidentificationnumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Workeruser{" + "id: " + super.getId() +
                ", users=" + users +
                ", personalidentificationnumber='" + personalidentificationnumber + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}