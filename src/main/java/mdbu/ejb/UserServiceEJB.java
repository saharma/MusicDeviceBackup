package mdbu.ejb;

import mdbu.entities.User;
import mdbu.daos.UserDao;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Created by saharmohamedali on 08/05/2017.
 */
@Stateless
@Local
public class UserServiceEJB implements UserService{
    @EJB
    private UserDao userDao;

    @Override
    public User getUser(String username, String password) {

        return userDao.getUser(username,password);
    }

    @Override
    public User findUserById(Integer id) {

        return userDao.findUserById(id);
    }

    @Override
    public void addUser(User user) {

        userDao.addUser(user);
    }
}