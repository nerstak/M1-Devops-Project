package control.session_beans;

import models.CommentsEntity;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless(name = "CommentsSessionEJB")
public class CommentsSessionBean extends ModelBean<CommentsEntity> {
    public CommentsEntity find(UUID id) {
        return em.find(CommentsEntity.class, id);
    }

    @Override
    public void save(CommentsEntity e) {
        em.merge(e);
    }
}
