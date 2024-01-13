package ar.juarce.models;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrayerFilter implements Filter<Prayer> {

    private final Long prayerRequestId;
    private final Long believerId;

    public PrayerFilter(Long prayerRequestId,Long believerId) {
        this.prayerRequestId = prayerRequestId;
        this.believerId = believerId;
    }

    @Override
    public Map<String, Object> getFilters() {
        return null;
    }

    @Override
    public void setFiltersToQuery(CriteriaBuilder criteriaBuilder, CriteriaQuery<Prayer> criteriaQuery, Root<Prayer> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (prayerRequestId != null) {
            predicates.add(criteriaBuilder.equal(root.get("prayerRequest").get("id"), prayerRequestId));
//            criteriaQuery.where(criteriaBuilder.equal(root.get("prayerRequest").get("id"), prayerRequestId));
        }
        if (believerId != null) {
            predicates.add(criteriaBuilder.equal(root.get("believer").get("id"), believerId));
//            criteriaQuery.where(criteriaBuilder.equal(root.get("believer").get("id"), believerId));
        }
        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long prayerRequestId;
        private Long believerId;

        public Builder prayerRequestId(Long prayerRequestId) {
            this.prayerRequestId = prayerRequestId;
            return this;
        }

        public Builder believerId(Long believerId) {
            this.believerId = believerId;
            return this;
        }

        public PrayerFilter build() {
            return new PrayerFilter(prayerRequestId, believerId);
        }
    }
}
