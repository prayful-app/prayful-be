package ar.juarce.persistence;

import ar.juarce.interfaces.PrayRequestDao;
import ar.juarce.interfaces.exceptions.NotFoundException;
import ar.juarce.models.PrayRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PrayRequestHibernateDao implements PrayRequestDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PrayRequest create(PrayRequest entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Optional<PrayRequest> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<PrayRequest> findAll() {
        return null;
    }

    @Override
    public PrayRequest update(Long aLong, PrayRequest entity) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(PrayRequest entity) {

    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }
}
