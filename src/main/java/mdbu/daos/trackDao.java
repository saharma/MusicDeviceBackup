package mdbu.daos;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by saharmohamedali on 05/05/2017.
 */

@Local
public interface trackDao {

    Collection<?> getAllTracks();
}
