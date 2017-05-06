package mdbu.daos;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * Created by saharmohamedali on 05/05/2017.
 */

@SuppressWarnings("ALL")
@Stateless
@Local

public class trackDaoImpl implements trackDao {

    @PersistenceContext
    EntityManager em;


    public Collection<?> getAllTracks(){
        Query query = em.createNamedQuery("getAllTracks");
        Collection<?> results = query.getResultList();
        return results;
    }

//    public void addTracks(List<track> tracks){
//        for(track track: tracks){
//            //service.parser();
//            em.persist(track);
//        }
//    }

}
