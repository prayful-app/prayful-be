package ar.juarce.persistence;

import ar.juarce.interfaces.PrayerRequestDao;
import ar.juarce.interfaces.exceptions.NotFoundException;
import ar.juarce.models.Filter;
import ar.juarce.models.PrayerRequest;
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
public class PrayerRequestHibernateDao implements PrayerRequestDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PrayerRequest create(PrayerRequest entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public Optional<PrayerRequest> findById(Long id) {
        return Optional.ofNullable(entityManager.find(PrayerRequest.class, id));
    }

    @Override
    public List<PrayerRequest> findAll(Filter<PrayerRequest> filter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PrayerRequest> criteriaQuery = criteriaBuilder.createQuery(PrayerRequest.class);
        Root<PrayerRequest> root = criteriaQuery.from(PrayerRequest.class);

        criteriaQuery.select(root);

        filter.setFiltersToQuery(criteriaBuilder, criteriaQuery, root);


        // Join with user table and filter by username
//        criteriaQuery.where(criteriaBuilder.equal(root.join("requester").get("username"), "clay"));
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createdAt")));

        TypedQuery<PrayerRequest> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public PrayerRequest update(Long aLong, PrayerRequest entity) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(PrayerRequest entity) {

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
