package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    List<User> listUsers();
    User getById(Long id);
    User findByUsername(String username);
    void delete(Long id);
}

//
//public  interface UserDao extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//}
