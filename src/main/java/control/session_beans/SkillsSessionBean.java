package control.session_beans;

import models.SkillsEntity;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Stateless(name = "SkillsSessionEJB")
public class SkillsSessionBean extends ModelBean<SkillsEntity> {
    public List getSkills() {
        Query q = em.createNamedQuery("Skills.SelectAll");
        return q.getResultList();
    }

    public List getSkillsByName(String s) {
        Query q = em.createNamedQuery("Skills.SelectByName");
        q.setParameter("skill",s);
        return q.getResultList();
    }

    public SkillsEntity getSkillByName(String s) {
        ArrayList<SkillsEntity> list = new ArrayList<>();
        list.addAll(getSkillsByName(s));
        if(!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public SkillsEntity find(UUID id) {
        return em.find(SkillsEntity.class,id);
    }

    @Override
    public void save(SkillsEntity e) {
        em.persist(e);
    }
}
