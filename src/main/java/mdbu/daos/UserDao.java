package mdbu.daos;

import mdbu.entities.User;

import javax.ejb.Local;

/**
 * Created by saharmohamedali on 08/05/2017.
 */

@Local
public interface UserDao {

    User getUser(String username, String password);

    User findUserById(Integer id);

    void addUser(User user);
}
