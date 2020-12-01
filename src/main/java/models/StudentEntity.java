package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity for Student table of Database
 */
@Entity
@Table(name = "student", schema = "public", catalog = "st2eedb")
public class StudentEntity implements InterfaceEntity, Serializable {
    // Attributes
    @Id
    @Column(name = "student_id", nullable = false, columnDefinition="uuid")
    private UUID studentId;

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;

    @Basic
    @Column(name = "firstname", nullable = true, length = -1)
    private String firstname;

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    private String email;

    @Basic
    @Column(name = "student_group", nullable = true, length = -1)
    private String studentGroup;

    @Basic
    @Column(name = "linkedin_profile", nullable = true, length = -1)
    private String linkedinProfile;

    // Relations
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn( name="tutor_id" )
    private TutorEntity tutorEntity;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable( name = "student_to_skills",
            joinColumns = @JoinColumn( name = "student_id" ),
            inverseJoinColumns = @JoinColumn( name = "skill_id" ) )
    private List<SkillsEntity> skills = new ArrayList<>();

    // Getters and Setters
    private TutorEntity getTutorEntity() {return tutorEntity;}

    public void setTutorEntity(TutorEntity tutorEntity) {
        this.tutorEntity = tutorEntity;
    }

    public List<SkillsEntity> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillsEntity> skills) {
        this.skills = skills;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public String getLinkedinProfile() {
        return linkedinProfile;
    }

    public void setLinkedinProfile(String linkedinProfile) {
        this.linkedinProfile = linkedinProfile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(studentGroup, that.studentGroup) &&
                Objects.equals(linkedinProfile, that.linkedinProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, name, firstname, email, studentGroup, linkedinProfile);
    }
}
