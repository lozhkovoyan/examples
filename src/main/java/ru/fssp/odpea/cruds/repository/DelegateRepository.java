package ru.fssp.odpea.cruds.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.model.Delegate;

@Repository
public interface DelegateRepository extends JpaRepository<Delegate, Long>, JpaSpecificationExecutor<Delegate> {
}