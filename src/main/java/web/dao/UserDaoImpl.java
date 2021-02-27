package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public void save(User user) {
        if (user.getId() == null) {
            entityManager.persist(user);
        } else {
            entityManager.merge(user);
        }
    }

    @Override
    public List<User> listUsers() {
        List<User> users = entityManager.createQuery("from User")
                .getResultList();
        return users;
    }

    @Override
    public User getById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :paramUsername",
                                                        User.class).setParameter("paramUsername", username)
                                                        .getResultList();
        return users.stream().findFirst().orElse(null);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }
}

//
//public class UserDaoImpl {
//}