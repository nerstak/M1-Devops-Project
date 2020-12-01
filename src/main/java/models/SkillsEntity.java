package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Entity for Skills table of Database
 */
@Entity
@Table(name = "skills", schema = "public", catalog = "st2eedb")
@NamedQueries({
        @NamedQuery(name = "Skills.SelectAll", query = "SELECT s FROM SkillsEntity s"),
        @NamedQuery(name = "Skills.SelectByName", query = "SELECT s FROM SkillsEntity s WHERE s.skill = :skill")
})
public class SkillsEntity implements InterfaceEntity, Serializable {
    // Attributes
    @Id
    @Column(name = "skill_id", nullable = false, columnDefinition="uuid")
    private UUID skillId;

    @Basic
    @Column(name = "skill", nullable = true, length = -1)
    private String skill;

    // Relations
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable( name = "student_to_skills",
            joinColumns = @JoinColumn( name = "skill_id" ),
            inverseJoinColumns = @JoinColumn( name = "student_id" ) )
    private List<StudentEntity> listStudents = new ArrayList<>();

    // Getters and Setters
    public UUID getSkillId() {
        return skillId;
    }

    public void setSkillId(UUID skillId) {
        this.skillId = skillId;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public List<StudentEntity> getListStudents() {
        return listStudents;
    }

    public void setListStudents(List<StudentEntity> listStudents) {
        this.listStudents = listStudents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillsEntity that = (SkillsEntity) o;
        return Objects.equals(skillId, that.skillId) &&
                Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, skill);
    }
}
