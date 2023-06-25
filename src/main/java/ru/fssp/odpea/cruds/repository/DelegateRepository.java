package ru.fssp.odpea.cruds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.fssp.odpea.object.Delegate;

@Repository
public interface DelegateRepository extends JpaRepository<Delegate, Long>, JpaSpecificationExecutor<Delegate> {
}