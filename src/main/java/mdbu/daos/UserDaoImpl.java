package mdbu.daos;

import mdbu.entities.User;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by saharmohamedali on 08/05/2017.
 */
@Stateless
@Local
public class UserDaoImpl implements  UserDao{
    @PersistenceContext
    EntityManager em;

    @Override
    public User getUser(String username, String password) {

        Query query = em.createNamedQuery("getUser");

        query.setParameter("username", username);
        query.setParameter("password", password);
        List<User> allUserInfo = query.getResultList();
        return allUserInfo.get(0);
    }

    @Override
    public void addUser(User user) {
        System.out.println("Made it to DAO");
        System.out.println(user.toString());
        em.persist(user);
    }


    // Annotation says that we do not need to open a transaction
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public User findUserById(Integer id) {
        return em.find(User.class, id);
    }


}
