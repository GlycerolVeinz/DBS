package Entities.User;

import Entities.Store.Store;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "workeruser")
public class WorkerUser {
    @EmbeddedId
    private WorkeruserId id;

    @MapsId("id")
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "usernickname", referencedColumnName = "nickname", nullable = false),
            @JoinColumn(name = "usermail", referencedColumnName = "email", nullable = false)
    })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User users;

    @ColumnDefault("nextval('workeruser_workerid_seq'::regclass)")
    @Column(name = "workerid", nullable = false)
    private Integer workerid;

    @Column(name = "personalidentificationnumber", nullable = false, length = 50)
    private String personalidentificationnumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "location")
    private Store store;

    public WorkeruserId getId() {
        return id;
    }

    public void setId(WorkeruserId id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

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

    public Store getStore() {
        return store;
    }

    public void setStore(Store location) {
        this.store = location;
    }

}