package control.session_beans;

import models.FinalReportEntity;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless(name = "FinalReportSessionEJB")
public class FinalReportSessionBean extends ModelBean<FinalReportEntity> {
    public FinalReportEntity find(UUID id) {
        return em.find(FinalReportEntity.class, id);
    }
}
