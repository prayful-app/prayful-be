package ar.juarce.models;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Map;

public class PrayerRequestFilter implements Filter<PrayerRequest> {

    private final Long requesterId;

    public PrayerRequestFilter(Long requesterId) {
        this.requesterId = requesterId;
    }

    @Override
    public Map<String, Object> getFilters() {
        return Map.of("requesterId", requesterId);
    }

    @Override
    public void setFiltersToQuery(CriteriaBuilder criteriaBuilder, CriteriaQuery<PrayerRequest> criteriaQuery, Root<PrayerRequest> root) {
        if (requesterId != null) {
            criteriaQuery.where(criteriaBuilder.equal(root.get("requester").get("id"), requesterId));
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long requesterId;

        public Builder requesterId(Long requesterId) {
            this.requesterId = requesterId;
            return this;
        }

        public PrayerRequestFilter build() {
            return new PrayerRequestFilter(requesterId);
        }
    }
}
