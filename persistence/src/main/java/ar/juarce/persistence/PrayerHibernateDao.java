package ar.juarce.persistence;

import ar.juarce.interfaces.PrayerDao;
import ar.juarce.interfaces.exceptions.AlreadyExistsException;
import ar.juarce.interfaces.exceptions.NotFoundException;
import ar.juarce.models.Filter;
import ar.juarce.models.Prayer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
    public Optional<Prayer> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Prayer.class, id));
    }

    @Override
    public List<Prayer> findAll(Filter<Prayer> filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Prayer> criteriaQuery = criteriaBuilder.createQuery(Prayer.class);
        Root<Prayer> root = criteriaQuery.from(Prayer.class);

        criteriaQuery.select(root);

        filter.setFiltersToQuery(criteriaBuilder, criteriaQuery, root);

        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdAt")));

        TypedQuery<Prayer> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
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
