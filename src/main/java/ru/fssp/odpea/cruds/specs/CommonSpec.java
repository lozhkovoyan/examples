package ru.fssp.odpea.cruds.specs;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.metamodel.SingularAttribute;

public class CommonSpec {
    private CommonSpec() {
    }

    public static <T, F> Specification<T> fieldEquals(
            final SingularAttribute<? super T, F> attribute, final T value) {
        return (root, query, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.<F>equal(root.get(attribute), value);
    }
}
