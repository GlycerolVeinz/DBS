package Entities.User;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "superiority")
public class Superiority {
    @Id
    @ColumnDefault("nextval('superiority_superiorityid_seq'::regclass)")
    @Column(name = "superiorityid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "superiorworkerid")
    private Workeruser superiorworkerid;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "underlingworkerid")
    private Workeruser underlingworkerid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Workeruser getSuperiorworkerid() {
        return superiorworkerid;
    }

    public void setSuperiorworkerid(Workeruser superiorworkerid) {
        this.superiorworkerid = superiorworkerid;
    }

    public Workeruser getUnderlingworkerid() {
        return underlingworkerid;
    }

    public void setUnderlingworkerid(Workeruser underlingworkerid) {
        this.underlingworkerid = underlingworkerid;
    }

}