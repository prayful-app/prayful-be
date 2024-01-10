package ar.juarce.models;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.Map;

public interface Filter<T> {

    Map<String, Object> getFilters();

    void setFiltersToQuery(CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery, Root<T> root);
}
