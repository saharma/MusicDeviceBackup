package mdbu.ejb;

import mdbu.daos.trackDao;
import mdbu.entities.track;

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


    public Collection<?> getAllTracks() {
        return trackDao.getAllTracks();
    }
}
