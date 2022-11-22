package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Rooms;

@ApplicationScoped
public class RoomsService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Rooms createRoom(Rooms rooms) {
        entityManager.persist(rooms);
        return rooms;
    }

    public List<Rooms> findAll() {
        var query = entityManager.createQuery("FROM Rooms", Rooms.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteRoom(Long id) {
        var entity = entityManager.find(Rooms.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Rooms updateRoom(Rooms rooms) {
        return entityManager.merge(rooms);
    }
}
