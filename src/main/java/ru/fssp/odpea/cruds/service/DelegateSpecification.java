package ru.fssp.odpea.cruds.service;

import org.springframework.data.jpa.domain.Specification;
import ru.fssp.odpea.object.Delegate;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class DelegateSpecification {
    public static Specification<Delegate> getFilterValueNameFirm(String valueNameFirm) {
        return (root, query, criteriaBuilder) -> valueNameFirm == null ? null : criteriaBuilder.like(
                criteriaBuilder.lower(root.get("valueNameFirm")),
                "%" + valueNameFirm.toLowerCase() + "%");
    }

/*

    public static Specification<Delegate> getDublicates(Delegate aboInputDelegate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicate = new ArrayList<>();
            predicate.add(criteriaBuilder.equal(root.get("valueWho"), aboInputDelegate.getValueNameFirm()));
            predicate.add(criteriaBuilder.equal(root.get("valueForWho"), aboInputDelegate.getValueInsteadNameFirm()));
            predicate.add(criteriaBuilder.equal(root.get("dtBeg"), aboInputDelegate.getDtBeg()));
            predicate.add(criteriaBuilder.equal(root.get("dtEnd"), aboInputDelegate.getDtEnd()));
            predicate.add(criteriaBuilder.equal(root.get("dType"), aboInputDelegate.getType()));
            predicate.add(criteriaBuilder.equal(root.get("isNowActive"), aboInputDelegate.getIsNowActive()));
            Predicate dublPredic = criteriaBuilder.and(predicate.toArray(new Predicate[0]));

            Predicate overlapPredicate = criteriaBuilder.and(
                    criteriaBuilder.lessThanOrEqualTo(root.get("dtBeg"), aboInputDelegate.getDtBeg()),
                    criteriaBuilder.greaterThanOrEqualTo(root.get("dtEnd"), aboInputDelegate.getDtEnd())
            );
            return criteriaBuilder.or(dublPredic, overlapPredicate);
        };
    }
*/

    /*public static <F>Specification<F> equalsField(final F value){
        return (root, query, criteriaBuilder) ->
                value == null ? null : criteriaBuilder.<F>equal(root.get())
    }*/
/*    public static Specification<Delegate> isValueWho(final String valueWho) {
        return CommonSpec.fieldEquals(Delegate_.valueWho, valueWho);
    }*/
}
