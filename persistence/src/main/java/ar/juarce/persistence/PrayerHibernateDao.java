package ar.juarce.persistence;

import ar.juarce.interfaces.PrayerDao;
import ar.juarce.interfaces.exceptions.AlreadyExistsException;
import ar.juarce.interfaces.exceptions.NotFoundException;
import ar.juarce.models.Filter;
import ar.juarce.models.Prayer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PrayerHibernateDao implements PrayerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Prayer create(Prayer entity) throws AlreadyExistsException {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Optional<Prayer> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Prayer> findAll(Filter<Prayer> filter) {
        return null;
    }

    @Override
    public Prayer update(Long aLong, Prayer entity) throws NotFoundException, AlreadyExistsException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Prayer entity) {

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
