package mdbu.daos;

import mdbu.entities.Track;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by saharmohamedali on 05/05/2017.
 */

@Local
public interface trackDao {

    void add(Track tack);

    Track getByID(Integer id);

    Collection<?> getAllTracks();

    void update(Track track);

    void remove(Integer track_id);






}
