package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity for FinalReport table of Database
 */
@Entity
@Table(name = "final_report", schema = "public", catalog = "st2eedb")
@NamedQueries({
        @NamedQuery(name = "FinalReportEntity.updateTitleReport", query = "update FinalReportEntity set title = :title Where finalReportId = :finalReportId"),
        @NamedQuery(name = "FinalReportEntity.updateReportBool", query = "update FinalReportEntity set report = :report Where finalReportId = :finalReportId")
    }
)
public class FinalReportEntity implements InterfaceEntity, Serializable {
    // Attributes
    @Id
    @Column(name = "final_report_id", nullable = false, columnDefinition="uuid")
    private UUID finalReportId;

    @Basic
    @Column(name = "title", nullable = true, length = -1)
    private String title;

    @Basic
    @Column(name = "report", nullable = true)
    private Boolean report;

    // Relations
    @OneToOne (cascade = CascadeType.MERGE)
    @JoinColumn( name="internship_id", nullable=true )
    InternshipEntity internship;

    // Getters and Setters
    public UUID getFinalReportId() {
        return finalReportId;
    }

    public void setFinalReportId(UUID finalReportId) {
        this.finalReportId = finalReportId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getReport() {
        return report;
    }

    public void setReport(Boolean report) {
        this.report = report;
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
        FinalReportEntity that = (FinalReportEntity) o;
        return Objects.equals(finalReportId, that.finalReportId) &&
                Objects.equals(title, that.title) &&
                Objects.equals(report, that.report);
    }

    @Override
    public int hashCode() {
        return Objects.hash(finalReportId, title, report);
    }
}
