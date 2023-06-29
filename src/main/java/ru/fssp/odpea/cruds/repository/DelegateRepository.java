package ru.fssp.odpea.cruds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.fssp.odpea.object.Delegate;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DelegateRepository extends JpaRepository<Delegate, Long>, JpaSpecificationExecutor<Delegate> {
//    boolean existsBy(Specification<Delegate> specification);

/*    @Query(value = "select case when count(ad) > 0 then true else false end " +
            "from DELEGATE ad " +
            "where ad.VALUE_NAME_FIRM = :valueNameFirm " +
            "and ad.VALUE_INSTEAD_NAME_FIRM = :valueInsteadNameFirm " +
            "and ad.DT_BEG = :dtBeg " +
            "and ad.DT_END = :dtEnd " +
            "and ad.TYPE = :type " +
            "and ad.IS_NOW_ACTIVE = :isNowActive ", nativeQuery = true)*/
    boolean existsByValueNameFirmAndValueInsteadNameFirmAndDtBegAndDtEnd(
             String valueNameFirm,
             String valueInsteadNameFirm,
             LocalDateTime dtBeg,
             LocalDateTime dtEnd
/*            @Param("type") Type type,
            @Param("isNowActive") Character isNowActive*/);
/*
    boolean existsByValueNameFirmAndValueInsteadNameFirmAndDtBegAndDtEndAndTypeAndIsNowActive(
            String valueNameFirm,
            String valueInsteadNameFirm,
            LocalDateTime dtBeg,
            LocalDateTime dtEnd,
            Type type,
            Character isNowActive);
*/

    List<Delegate> findByValueNameFirm(String valueNameFirm);
}