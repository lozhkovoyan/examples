package ru.fssp.odpea.cruds.specs;

import org.springframework.data.jpa.domain.Specification;
import ru.fssp.odpea.object.Delegate;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DelegateSpecification {
    public static Specification<Delegate> getFilterValueNameFirm(String valueNameFirm) {
        return (root, query, criteriaBuilder) -> valueNameFirm == null ? null : criteriaBuilder.like(
                criteriaBuilder.lower(root.get("valueNameFirm")),
                "%" + valueNameFirm.toLowerCase() + "%");
    }


    public static Specification<Delegate> getDublicates(
            String valueNameFirm,
            String valueInsteadNameFirm,
            LocalDateTime dtBeg,
            LocalDateTime dtEnd,
            String type,
            Character isNowActive) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            predicate.add(criteriaBuilder.equal(root.get("valueNameFirm"), valueNameFirm));
            predicate.add(criteriaBuilder.equal(root.get("valueInsteadNameFirm"), valueInsteadNameFirm));
            predicate.add(criteriaBuilder.equal(root.get("dtBeg"), dtBeg));
            predicate.add(criteriaBuilder.equal(root.get("dtEnd"), dtEnd));
            predicate.add(criteriaBuilder.equal(root.get("type"), type));
            predicate.add(criteriaBuilder.equal(root.get("isNowActive"), isNowActive));
            Predicate dublPredic = criteriaBuilder.and(predicate.toArray(new Predicate[0]));

            Predicate overlapPredicate = criteriaBuilder.and(
                    criteriaBuilder.lessThanOrEqualTo(root.get("dtBeg"), dtBeg),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dtEnd"), dtEnd));
            Predicate partOverlapPredicate = criteriaBuilder.and(
                    criteriaBuilder.lessThanOrEqualTo(root.get("dtBeg"), dtBeg),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dtEnd"), dtBeg));
            Predicate partOverlapPredicate2 = criteriaBuilder.and(
                    criteriaBuilder.lessThanOrEqualTo(root.get("dtBeg"), dtEnd),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dtEnd"), dtEnd)
            );
//            добавить информативный лог, какое из условий выполнилось :)
            return criteriaBuilder.or(dublPredic, overlapPredicate, partOverlapPredicate2, partOverlapPredicate);
        };
    }


    /*public static <F>Specification<F> equalsField(final F value){
        return (root, query, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.<F>equal(root.get())
    }*/
/*    public static Specification<Delegate> isValueWho(final String valueWho) {
        return CommonSpec.fieldEquals(Delegate_.valueWho, valueWho);
    }*/
}
