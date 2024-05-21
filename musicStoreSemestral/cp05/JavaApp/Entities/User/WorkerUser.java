package Entities.User;

import Entities.Store.Store;

import javax.persistence.*;

@Entity
@Table(name = "workeruser")
@AttributeOverrides({
        @AttributeOverride(name = "id.nickname", column = @Column(name = "worker_nickname")),
        @AttributeOverride(name = "id.email", column = @Column(name = "worker_email"))
})
public class WorkerUser extends User {
    @Column(name = "workerid", nullable = false)
    private Integer workerid;

    @Column(name = "personalidentificationnumber", nullable = false, length = 50)
    private String personalidentificationnumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location")
    private Store location;

    public Integer getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Integer workerid) {
        this.workerid = workerid;
    }

    public String getPersonalidentificationnumber() {
        return personalidentificationnumber;
    }

    public void setPersonalidentificationnumber(String personalidentificationnumber) {
        this.personalidentificationnumber = personalidentificationnumber;
    }

    public Store getLocation() {
        return location;
    }

    public void setLocation(Store location) {
        this.location = location;
    }
}
