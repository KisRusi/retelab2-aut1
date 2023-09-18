package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import hu.bme.aut.retelab2.domain.Note;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AdRepository {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad feedback) {
        return em.merge(feedback);
    }

    public List<Ad> findAll() {
        return em.createQuery("SELECT a FROM Ad a", Ad.class).getResultList();
    }

    public List<Ad> findByMinMax(int min, int max) {
        return em.createQuery("SELECT a FROM Ad a WHERE a.price BETWEEN ?1 AND ?2", Ad.class).setParameter(1, min).setParameter(2,max).getResultList();
    }

    public List<Ad> findByKeyword(String keyword) {
        return em.createQuery("SELECT n FROM Ad n WHERE n.title LIKE ?1", Ad.class).setParameter(1, '%' + keyword + '%').getResultList();
    }

    public Ad findWithSecretCode (Ad ad)
    {
        Ad temp = new Ad();
        try{
            temp =  em.createQuery("SELECT a FROM Ad a WHERE a.id LIKE ?1 AND a.secretCode LIKE ?2", Ad.class).setParameter(1,ad.getId()).setParameter(2,ad.getSecretCode()).getSingleResult();}

        catch (NoResultException e){
            temp = null;
        }
        return temp;
    }

    @Transactional
    @Scheduled(fixedDelay= 6000)
    public void deleteClosedAds()
    {
        List<Ad> temp = findAll();
        for(int i = 0; i < temp.size(); i++)
        {
            if(temp.get(i).getCloseDate().isBefore(LocalDateTime.now().plusHours(1)))
            {
                em.remove(temp.get(i));
            }
        }
    }


}
