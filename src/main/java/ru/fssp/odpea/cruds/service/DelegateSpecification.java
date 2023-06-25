package ru.fssp.odpea.cruds.service;

import org.springframework.data.jpa.domain.Specification;
import ru.fssp.odpea.object.Delegate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DelegateSpecification {
    public static Specification<Delegate> hasFilterValueNameFirm(String valueNameFirm) {
        return (Root<Delegate> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) ->
                valueNameFirm == null ? null : criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("valueNameFirm")),
                        "%" + valueNameFirm.toLowerCase() + "%");
    }
}
