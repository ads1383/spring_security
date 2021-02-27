package web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface RoleDao {
    Set<Role> hashSetRolesByListId(List<Long> rolesId);
    void save(Role role);
    List<Role> listRoles();
    Role getById(Long id);
    void delete(Long id);
}

//
//public interface RoleDao extends JpaRepository<Role, Long> {}
