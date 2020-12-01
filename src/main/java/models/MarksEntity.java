package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity for Marks table of Database
 */
@Entity
@Table(name = "marks", schema = "public", catalog = "st2eedb")
@NamedQueries(
        @NamedQuery(name = "MarksEntity.updateMarks", query = "update MarksEntity set tech = :tech, communication = :communication Where marksId = :marksId")
)
public class MarksEntity implements InterfaceEntity, Serializable {
    // Attributes
    @Id
    @Column(name = "marks_id", nullable = false, columnDefinition="uuid")
    private UUID marksId;

    @Basic
    @Column(name = "tech", nullable = true)
    private Integer tech;

    @Basic
    @Column(name = "communication", nullable = true)
    private Integer communication;

    // Relations
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn( name="internship_id", nullable=true )
    private InternshipEntity internship;

    // Getters and Setters
    public UUID getMarksId() {
        return marksId;
    }

    public void setMarksId(UUID marksId) {
        this.marksId = marksId;
    }

    public Integer getTech() {
        return tech;
    }

    public void setTech(Integer tech) {
        this.tech = tech;
    }

    public Integer getCommunication() {
        return communication;
    }

    public void setCommunication(Integer communication) {
        this.communication = communication;
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
        MarksEntity that = (MarksEntity) o;
        return Objects.equals(marksId, that.marksId) &&
                Objects.equals(tech, that.tech) &&
                Objects.equals(communication, that.communication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marksId, tech, communication);
    }
}
