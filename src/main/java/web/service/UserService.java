package web.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {
    List<Role> listRoles();
    Set<Role> hashSetRolesByListId(List<Long> rolesId);
    User findByUsername(String username);
    void save(User user);
    List<User> listUsers();
    User getById(Long id);
    void delete(Long id);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;
}

//public interface UserService {
//    void save(User user);
//    User findByUsername(String username);
//}