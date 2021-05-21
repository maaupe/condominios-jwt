package br.com.condominio.app.modelo;

import javax.persistence.EntityManager;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import br.com.condominio.app.config.BeanUtil;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static br.com.condominio.app.modelo.Action.*;

/**
 * @author Naresh Joshi
 */

public class TituloListener {

    @PrePersist
    public void prePersist(Titulo target) {
        perform(target, INSERTED);
    }

    @PreUpdate
    public void preUpdate(Titulo target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(Titulo target) {
        perform(target, DELETED);
    }

   @Transactional(MANDATORY)
    private void perform(Titulo target, Action action) {
        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
        //entityManager.persist(new TituloHistory(target, action));
    }

}
