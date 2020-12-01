package control.session_beans;

import models.VisitEntity;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless(name = "VisitSessionEJB")
public class VisitSessionBean extends ModelBean<VisitEntity> {
    public VisitEntity find(UUID id) {
        return em.find(VisitEntity.class, id);
    }
}
