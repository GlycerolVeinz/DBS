package Entities.User;

import javax.persistence.*;

@Entity
@Table(name = "superiority")
public class Superiority {
    @Id
    @Column(name = "superiorityid", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "superiorid", referencedColumnName = "workerid")
    private WorkerUser superiorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "underlingid", referencedColumnName = "workerid")
    private WorkerUser underlingid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issuperior", referencedColumnName = "personalidentificationnumber")
    private WorkerUser issuperior;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "isunderling", referencedColumnName = "personalidentificationnumber")
    private WorkerUser isunderling;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public WorkerUser getSuperiorid() {
        return superiorid;
    }

    public void setSuperiorid(WorkerUser superiorid) {
        this.superiorid = superiorid;
    }

    public WorkerUser getUnderlingid() {
        return underlingid;
    }

    public void setUnderlingid(WorkerUser underlingid) {
        this.underlingid = underlingid;
    }

    public WorkerUser getIssuperior() {
        return issuperior;
    }

    public void setIssuperior(WorkerUser issuperior) {
        this.issuperior = issuperior;
    }

    public WorkerUser getIsunderling() {
        return isunderling;
    }

    public void setIsunderling(WorkerUser isunderling) {
        this.isunderling = isunderling;
    }

}