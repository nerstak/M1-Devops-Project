package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity for Visit table of Database
 */
@Entity
@Table(name = "visit", schema = "public", catalog = "st2eedb")
@NamedQueries(
        @NamedQuery(name = "VisitEntity.UpdateVisit", query = "update VisitEntity set done = :done, planned = :planned Where visitId = :visitId")
)
public class VisitEntity implements InterfaceEntity, Serializable {
    // Attributes
    @Id
    @Column(name = "visit_id", nullable = false, columnDefinition="uuid")
    private UUID visitId;

    @Basic
    @Column(name = "done", nullable = true)
    private Boolean done;

    @Basic
    @Column(name = "planned", nullable = true)
    private Boolean planned;

    @Basic
    @Column(name = "visit_report", nullable = true)
    private Boolean visitReport;

    // Relations
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn( name="internship_id", nullable=true )
    private InternshipEntity internship;

    // Getters and Setters
    public UUID getVisitId() {
        return visitId;
    }

    public void setVisitId(UUID visitId) {
        this.visitId = visitId;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }

    public Boolean getVisitReport() {
        return visitReport;
    }

    public void setVisitReport(Boolean visitReport) {
        this.visitReport = visitReport;
    }

    public InternshipEntity getInternship() {
        return internship;
    }

    public void setInternship(InternshipEntity internship) {
        this.internship = internship;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitEntity that = (VisitEntity) o;
        return Objects.equals(visitId, that.visitId) &&
                Objects.equals(done, that.done) &&
                Objects.equals(planned, that.planned) &&
                Objects.equals(visitReport, that.visitReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitId, done, planned, visitReport);
    }
}
