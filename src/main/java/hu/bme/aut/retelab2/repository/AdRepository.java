package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public class AdRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad feedback) {
        feedback.setPublishDate();
        return em.merge(feedback);
    }
}
