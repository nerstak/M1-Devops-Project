package control.session_beans;

import models.InternshipEntity;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Stateless(name = "InternshipSessionEJB")
public class InternshipSessionBean extends ModelBean<InternshipEntity> {
    public List getInternshipData(UUID tutorId, String year, String name, String keyword) {
        Query q = em.createNamedQuery("Internship.SelectList");
        q.setParameter("tutor",tutorId);
        q.setParameter("year",year);
        q.setParameter("name",name);
        q.setParameter("keyword",keyword);
        return q.getResultList();
    }

    public List getInternship(UUID internshipId) {
        Query q = em.createNamedQuery("Internship.SelectSingle");
        q.setParameter("internshipId", internshipId);
        return q.getResultList();
    }

    public InternshipEntity find(UUID id) {
        return em.find(InternshipEntity.class, id);
    }
}
