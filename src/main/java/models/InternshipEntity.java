package models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity for Internship table of Database
 */
@Entity
@Table(name = "internship", schema = "public", catalog = "st2eedb")
@NamedQueries({
        @NamedQuery(name = "Internship.SelectList", query = "SELECT DISTINCT i FROM InternshipEntity i LEFT JOIN i.keywords ik WHERE i.student.tutorEntity.tutorId = :tutor AND" +
                                                                " FUNCTION('to_char', i.beginning, 'YYYY') LIKE :year AND" +
                                                                " CONCAT(i.student.firstname, ' ', i.student.name) LIKE CONCAT('%',:name,'%') AND" +
                                                                " :keyword IN (ik.keyword, '-')"+
                                                                " ORDER BY i.beginning DESC"),
        @NamedQuery(name = "Internship.SelectSingle", query = "SELECT i FROM InternshipEntity i WHERE i.internshipId = :internshipId")
})
public class InternshipEntity implements InterfaceEntity, Serializable {
    // Attributes
    @Id    @Column(name = "internship_id", nullable = false, columnDefinition="uuid") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID internshipId;

    @Basic    @Column(name = "description", nullable = true, length = -1)
    private String description;

    @Basic    @Column(name = "web_survey", nullable = true)
    private Boolean webSurvey;

    @Basic    @Column(name = "mid_intern_info", nullable = true)
    private Boolean midInternInfo;

    @Basic    @Column(name = "beginning", nullable = true)
    private Date beginning;

    @Basic    @Column(name = "ending", nullable = true)
    private Date ending;

    @Basic    @Column(name = "cdc", nullable = true)
    private Boolean cdc;

    @Basic    @Column(name = "company_eval", nullable = true)
    private Boolean companyEval;

    @Basic    @Column(name = "defense", nullable = true)
    private Boolean defense;

    @Basic    @Column(name = "intern_supervisor", nullable = true, length = -1)
    private String internSupervisor;

    // Relations
    @OneToOne( mappedBy = "internship", cascade = CascadeType.MERGE )
    private MarksEntity marks;

    @OneToOne( mappedBy = "internship", cascade = CascadeType.MERGE )
    private CommentsEntity comments;

    @OneToOne( mappedBy = "internship", cascade = CascadeType.MERGE )
    private FinalReportEntity finalReport;

    @OneToOne( mappedBy = "internship", cascade = CascadeType.MERGE )
    private VisitEntity visit;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn( name="company_id" )
    private CompanyEntity company;

    @ManyToOne   (cascade = CascadeType.MERGE)
    @JoinColumn( name="student_id" )
    private StudentEntity student;

    @ManyToMany (cascade = CascadeType.MERGE)
    @JoinTable( name = "internship_to_keywords",
            joinColumns = @JoinColumn( name = "internship_id" ),
            inverseJoinColumns = @JoinColumn( name = "keyword_id" ) )
    private List<KeywordsEntity> keywords = new ArrayList<>();

    // Getters and setters
    public FinalReportEntity getFinalReport() {return finalReport;}

    public void setFinalReport(FinalReportEntity finalReport) {
        this.finalReport = finalReport;
    }

    public CommentsEntity getComments() {return comments;}

    public void setComments(CommentsEntity comments) {
        this.comments = comments;
    }

    public MarksEntity getMarks() {return marks;}

    public void setMarks(MarksEntity marks) {
        this.marks = marks;
    }

    public VisitEntity getVisit() {return visit;}

    public void setVisit(VisitEntity visit) {
        this.visit = visit;
    }

    public StudentEntity getStudent() {return student;}

    public void setStudent(StudentEntity student) {
        this.student = student;
    }

    public CompanyEntity getCompany() {return company;}

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public List getListKeywords() {return keywords;}

    public void setKeywords(List<KeywordsEntity> keywords) {
        this.keywords = keywords;
    }

    public UUID getInternshipId() {
        return internshipId;
    }

    public void setInternshipId(UUID internshipId) {
        this.internshipId = internshipId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getWebSurvey() {
        return webSurvey;
    }

    public void setWebSurvey(Boolean webSurvey) {
        this.webSurvey = webSurvey;
    }

    public Boolean getMidInternInfo() {
        return midInternInfo;
    }

    public void setMidInternInfo(Boolean midInternInfo) {
        this.midInternInfo = midInternInfo;
    }

    public Date getBeginning() {
        return beginning;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public Date getEnding() {
        return ending;
    }

    public void setEnding(Date ending) {
        this.ending = ending;
    }

    public Boolean getCdc() {
        return cdc;
    }

    public void setCdc(Boolean cdc) {
        this.cdc = cdc;
    }

    public Boolean getCompanyEval() {
        return companyEval;
    }

    public void setCompanyEval(Boolean companyEval) {
        this.companyEval = companyEval;
    }

    public Boolean getDefense() {
        return defense;
    }

    public void setDefense(Boolean defense) {
        this.defense = defense;
    }

    public String getInternSupervisor() {
        return internSupervisor;
    }

    public void setInternSupervisor(String internSupervisor) {
        this.internSupervisor = internSupervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternshipEntity that = (InternshipEntity) o;
        return Objects.equals(internshipId, that.internshipId) &&
                Objects.equals(description, that.description) &&
                Objects.equals(webSurvey, that.webSurvey) &&
                Objects.equals(midInternInfo, that.midInternInfo) &&
                Objects.equals(beginning, that.beginning) &&
                Objects.equals(ending, that.ending) &&
                Objects.equals(cdc, that.cdc) &&
                Objects.equals(companyEval, that.companyEval) &&
                Objects.equals(defense, that.defense) &&
                Objects.equals(internSupervisor, that.internSupervisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internshipId, description, webSurvey, midInternInfo, beginning, ending, cdc, companyEval, defense, internSupervisor);
    }
}
