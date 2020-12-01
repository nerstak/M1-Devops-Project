package control.session_beans;

import models.MarksEntity;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless(name = "MarksSessionEJB")
public class MarksSessionBean extends ModelBean<MarksEntity>{
    public MarksEntity find(UUID id) {
        return em.find(MarksEntity.class, id);
    }
}
