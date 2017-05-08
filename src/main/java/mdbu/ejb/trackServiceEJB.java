package mdbu.ejb;

import mdbu.daos.trackDao;
import mdbu.entities.Track;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.Collection;

/**
 * Created by saharmohamedali on 05/05/2017.
 */

@Stateless
@Local
public class trackServiceEJB implements  trackService{

    @EJB
    private trackDao trackDao;

    @Override
    public void add(Track track) {
        trackDao.add(track);
    }

    @Override
    public Track getById(Integer id) {

        return trackDao.getByID(id);
    }


    public Collection<?> getAllTracks() {

        return trackDao.getAllTracks();
    }

    public void update(Track Track){
        trackDao.update(Track);
    }

    public void remove(Integer track_id){
        trackDao.remove(track_id);
    }
}
