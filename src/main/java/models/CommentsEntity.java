package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity for Comments table of Database
 */
@Entity
@Table(name = "comments", schema = "public", catalog = "st2eedb")
@NamedQueries(
        @NamedQuery(name = "CommentsEntity.updateComments", query = "update CommentsEntity set studentComm = :studentComm, supervisorComm = :supervisorComm Where commentsId = :commentsId")
)
public class CommentsEntity implements InterfaceEntity, Serializable {
    // Attributes
    @Id
    @Column(name = "comments_id", nullable = false, columnDefinition="uuid")
    private UUID commentsId;
    @Basic
    @Column(name = "student_comm", nullable = true, length = -1)
    private String studentComm;

    @Basic
    @Column(name = "supervisor_comm", nullable = true, length = -1)
    private String supervisorComm;

    // Relations
    @OneToOne (cascade = CascadeType.MERGE)
    @JoinColumn( name="internship_id", nullable=true )
    private InternshipEntity internship;

    // Getters and Setters
    public UUID getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(UUID commentsId) {
        this.commentsId = commentsId;
    }

    public String getStudentComm() {
        return studentComm;
    }

    public void setStudentComm(String studentComm) {
        this.studentComm = studentComm;
    }

    public String getSupervisorComm() {
        return supervisorComm;
    }

    public void setSupervisorComm(String supervisorComm) {
        this.supervisorComm = supervisorComm;
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
        CommentsEntity that = (CommentsEntity) o;
        return Objects.equals(commentsId, that.commentsId) &&
                Objects.equals(studentComm, that.studentComm) &&
                Objects.equals(supervisorComm, that.supervisorComm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentsId, studentComm, supervisorComm);
    }
}
