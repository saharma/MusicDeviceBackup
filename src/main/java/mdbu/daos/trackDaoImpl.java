package mdbu.daos;

import mdbu.entities.track;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Created by saharmohamedali on 05/05/2017.
 */

@SuppressWarnings("ALL")
@Stateless
@Local

public class trackDaoImpl implements trackDao {

    @PersistenceContext
    EntityManager em;


    public Collection<track> getAllTracks(){
        Query query = em.createNamedQuery("getAllTracks");
        //List<track> list = query.getResultList();
        return (List<track>) query.getResultList();
    }

//    public void addTracks(List<track> tracks){
//        for(track track: tracks){
//            //service.parser();
//            em.persist(track);
//        }
//    }

}
