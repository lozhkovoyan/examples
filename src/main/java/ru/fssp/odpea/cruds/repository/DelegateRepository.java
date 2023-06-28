package ru.fssp.odpea.cruds.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.fssp.odpea.object.Delegate;

import java.util.List;

@Repository
public interface DelegateRepository extends JpaRepository<Delegate, Long>, JpaSpecificationExecutor<Delegate> {
    boolean existsBy(Specification<Delegate> specification);
    /*    @Query(value = "select case when count(ad) > 0 then true else false end " +
                "from DELEGATE ad " +
                "where ad.VALUE_NAME_FIRM = :valueNameFirm " +
                "and ad.VALUE_INSTEAD_NAME_FIRM = :valueInsteadNameFirm " +
                "and ad.DT_BEG = :dtBeg " +
                "and ad.DT_END = :dtEnd " +
                "and ad.TYPE = :type " +
                "and ad.IS_NOW_ACTIVE = :isNowActive ", nativeQuery = true)
        boolean existsForDublicates(
                @Param("valueNameFirm") String valueNameFirm,
                @Param("valueInsteadNameFirm") String valueInsteadNameFirm,
                @Param("dtBeg")ZonedDateTime dtBeg,
                @Param("dtEnd")ZonedDateTime dtEnd,
                @Param("type")Type type,
                @Param("isNowActive")Character isNowActive);*/
    /*boolean existsByvalueNameFirmAndvalueInsteadNameFirmAndDtBegAndDtEndAndTypeAndIsNowActive(
            String valueNameFirm,
            String valueInsteadNameFirm,
            ZonedDateTime dtBeg,
            ZonedDateTime dtEnd,
            Type type,
            Character isNowActive);
*/
    List<Delegate> findByValueWho(String valueWho);
}