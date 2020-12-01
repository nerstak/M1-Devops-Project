package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity for Tutor table of Database
 */
@Entity
@Table(name = "tutor", schema = "public", catalog = "st2eedb")
@NamedQueries(
        @NamedQuery(name = "Tutor.SelectSingleTutor", query = "SELECT t FROM TutorEntity t WHERE t.email  = :email AND t.pwd = :pwd")
)
public class TutorEntity implements InterfaceEntity, Serializable {
    // Attributes
    @Id
    @Column(name = "tutor_id", nullable = false, columnDefinition="uuid") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID tutorId;

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;

    @Basic
    @Column(name = "firstname", nullable = true, length = -1)
    private String firstname;

    @Basic
    @Column(name = "pwd", nullable = true, length = -1)
    private String pwd;

    @Basic
    @Column(name = "email", nullable = true, length = -1)
    private String email;

    // Relations
    @OneToMany(mappedBy="tutorEntity",cascade = CascadeType.MERGE)
    private List<StudentEntity> students = new ArrayList<>();

    // Getters and Setters
    public UUID getTutorId() {
        return tutorId;
    }

    public void setTutorId(UUID tutorId) {
        this.tutorId = tutorId;
    }

    public List getListStudents() { return students; }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TutorEntity that = (TutorEntity) o;
        return Objects.equals(tutorId, that.tutorId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(pwd, that.pwd) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tutorId, name, firstname, pwd, email);
    }


}
