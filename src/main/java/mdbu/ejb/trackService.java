package mdbu.ejb;

import mdbu.entities.Track;

import java.util.Collection;

/**
 * Created by saharmohamedali on 05/05/2017.
 */
public interface trackService {

    void add(Track track);

    Track getById(Integer id);

    Collection<?> getAllTracks();

    void update(Track track);

    void remove(Integer track_id);
}
