package control.session_beans;

import models.InterfaceEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

public abstract class ModelBean<T extends InterfaceEntity> {
    @PersistenceContext
    protected EntityManager em;

    public void save(T e) {
        em.merge(e);
    }

    public abstract T find(UUID id);
}
