package control.session_beans;

import models.KeywordsEntity;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Stateless(name = "KeywordsSessionEJB")
public class KeywordsSessionBean extends ModelBean<KeywordsEntity>{
    public List getKeywords() {
        Query q = em.createNamedQuery("Keywords.SelectAll");
        return q.getResultList();
    }

    @Override
    public KeywordsEntity find(UUID id) {
        em.find(KeywordsEntity.class,id);
        return null;
    }

    @Override
    public void save(KeywordsEntity e) {
        em.persist(e);
    }

    public List getKeywordsByName(String s) {
        Query q = em.createNamedQuery("Keywords.SelectByName");
        q.setParameter("keyword",s);
        return q.getResultList();
    }

    public KeywordsEntity getKeywordByName(String s) {
        ArrayList<KeywordsEntity> list = new ArrayList<>();
        list.addAll(getKeywordsByName(s));
        if(!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
}
