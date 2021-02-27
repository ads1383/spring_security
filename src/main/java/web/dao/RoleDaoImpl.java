package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public  Set<Role> hashSetRolesByListId(List<Long> rolesId) {
        return rolesId.stream()
                .map(p -> getById(p))
                .filter(value -> value != null)
                .collect(Collectors.toSet());
    }

    @Override
    public void save(Role role) {
        if (role.getId() == null) {
            entityManager.persist(role);
        } else {
            entityManager.merge(role);
        }
    }

    @Override
    public List<Role> listRoles() {
        List<Role> roles = entityManager.createQuery("from Role")
                .getResultList();
        return roles;
    }

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(getById(id));
    }
}
//
//public class RoleDaoImpl {
//}