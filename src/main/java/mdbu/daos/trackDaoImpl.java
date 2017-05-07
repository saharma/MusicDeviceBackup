package mdbu.daos;

import mdbu.entities.Track;

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


    @Override
    public void add(Track track) {
        Query query = em.createNamedQuery("getAllTracks");
        Collection<?> results = query.getResultList();
        if (!results.contains(track)) {
            em.persist(track);
        }

    }

    @Override
    public Track getByID(Integer id) {
        Query query = em.createQuery("from Track where id = :id").setParameter("id", id);
        Track tmp = (Track) query.getSingleResult();
        if (tmp != null) {
            System.out.println("Id found");
        } else {
            System.out.println("Id not found");
        }
        return tmp;
    }

    public Collection<?> getAllTracks() {
        Query query = em.createNamedQuery("getAllTracks");
        Collection<?> results = query.getResultList();
        return results;
    }

    public void update(Track track) {
        Query query = em.createNamedQuery("getAllTracks");
        List<Track> results = query.getResultList();
        for (Track t : results) {
            if (t.getId().equals(track.getId())) {
                em.merge(track);
            } else {
                System.out.print("Not found");
            }

        }

    }

    public void remove(Integer track_id) {
        Query query = em.createQuery("from Track where track_id = :track_id").setParameter("track_id", track_id);
        Track tmp = (Track) query.getSingleResult();
        if (tmp != null) {
            em.remove(tmp);
            System.out.println("Track" + track_id + " removed!");
        } else {
            System.out.println("Track Id not found");
        }
    }

}



