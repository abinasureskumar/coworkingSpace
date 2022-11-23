package ch.zli.m223.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Users;

@ApplicationScoped
public class UsersService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Users createUser(Users users) {
        entityManager.persist(users);
        return users;
    }

    public List<Users> findAll() {
        var query = entityManager.createQuery("FROM Users", Users.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteUser(Long id) {
        var entity = entityManager.find(Users.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Users updateUser(Users users) {
        return entityManager.merge(users);
    }

    public Optional<Users> findByEmail(String email) {
        return entityManager
                .createNamedQuery("ApplicationUser.findByEmail", Users.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst();
    }
}
