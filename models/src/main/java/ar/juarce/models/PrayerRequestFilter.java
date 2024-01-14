package ar.juarce.models;

import jakarta.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrayerRequestFilter implements Filter<PrayerRequest> {

    private final Long requesterId;
    private final Long recommendedForId;

    public PrayerRequestFilter(Long requesterId, Long recommendedForId) {
        this.requesterId = requesterId;
        this.recommendedForId = recommendedForId;
    }

    @Override
    public Map<String, Object> getFilters() {
        return Map.of("requesterId", requesterId);
    }

    @Override
    public void setFiltersToQuery(CriteriaBuilder criteriaBuilder, CriteriaQuery<PrayerRequest> criteriaQuery, Root<PrayerRequest> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (requesterId != null) {
            predicates.add(criteriaBuilder
                    .equal(root.get("requester").get("id"), requesterId));
        }
        if (recommendedForId != null) {
            predicates.add(criteriaBuilder
                    .notEqual(root.get("requester").get("id"), recommendedForId));

            Subquery<Long> subquery = criteriaQuery.subquery(Long.class);
            Root<Prayer> prayerRoot = subquery.from(Prayer.class);
            subquery.select(prayerRoot.get("prayerRequest").get("id"))
                    .distinct(true)
                    .where(criteriaBuilder.equal(prayerRoot.get("believer").get("id"), recommendedForId));
            predicates.add(criteriaBuilder
                    .not(root.get("id").in(subquery)));
        }
        if (!predicates.isEmpty()) {
            criteriaQuery.where(predicates.toArray(Predicate[]::new));
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long requesterId;
        private Long recommendedForId;

        public Builder requesterId(Long requesterId) {
            this.requesterId = requesterId;
            return this;
        }

        public Builder recommendedForId(Long recommendedForId) {
            this.recommendedForId = recommendedForId;
            return this;
        }

        public PrayerRequestFilter build() {
            return new PrayerRequestFilter(requesterId, recommendedForId);
        }
    }
}
