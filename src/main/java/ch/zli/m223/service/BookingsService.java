package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.Bookings;

@ApplicationScoped
public class BookingsService {
    @Inject
    private EntityManager entityManager;

    @Transactional
    public Bookings createBooking(Bookings bookings) {
        entityManager.persist(bookings);
        return bookings;
    }

    public List<Bookings> findAll() {
        var query = entityManager.createQuery("FROM Bookings", Bookings.class);
        return query.getResultList();
    }

    @Transactional
    public void deleteBooking(Long id) {
        var entity = entityManager.find(Bookings.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public Bookings updateBooking(Bookings bookings) {
        return entityManager.merge(bookings);
    }
}
