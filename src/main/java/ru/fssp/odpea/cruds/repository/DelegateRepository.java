package ru.fssp.odpea.cruds.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fssp.odpea.cruds.model.Delegate;

@Repository
public interface DelegateRepository extends JpaRepository<Delegate, Long> {
    Page<Delegate> findAllByValueNameFirm(String valueNameFirm, Pageable pageable);
}
