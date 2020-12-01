package control.session_beans;

import models.TutorEntity;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Stateless(name = "TutorSessionBeanEJB")
public class TutorSessionBean extends ModelBean<TutorEntity> {
    @Override
    public TutorEntity find(UUID id) {
        return em.find(TutorEntity.class, id);
    }

    public List getTutors(String email, String pwd) {
        Query q = em.createNamedQuery("Tutor.SelectSingleTutor");
        q.setParameter("email",email);
        q.setParameter("pwd", pwd);
        return q.getResultList();
    }
}
