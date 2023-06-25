package ru.fssp.odpea.cruds.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.dto.DelegateDtoResponse;
import ru.fssp.odpea.cruds.mapper.DelegateMapper;
import ru.fssp.odpea.object.Delegate;
import ru.fssp.odpea.cruds.repository.DelegateRepository;
import ru.fssp.odpea.cruds.service.DelegateSpecification;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Slf4j
public class DelegateServiceImpl {

    private final DelegateRepository delegateRepository;

    public DelegateServiceImpl(DelegateRepository delegateRepository) {
        this.delegateRepository = delegateRepository;
    }

    public Page<Delegate> findAllWithValueNameFirm(Pageable pageable, Optional<String> valueNameFirm) {
        log.info("Сервис обработки вернуть всех делегатов с фильтром {} и пагинацией {} при наличии", valueNameFirm, pageable);
        Page<Delegate> delegatePageList = null;
        Specification<Delegate> specification = valueNameFirm.map(DelegateSpecification::hasFilterValueNameFirm).orElse(null);
        try {
//            Specification<Delegate> specification = DelegateSpecification.hasFilterValueNameFirm(valueNameFirm);
            delegatePageList = delegateRepository.findAll(specification, pageable);
            if (delegatePageList.isEmpty()) {
                throw new IOException("delegatePageList.isEmpty() - " + delegatePageList.isEmpty());
            }
            log.info("Получен список из бд с пагинацией valueNameFirm {} с полученный запрос {}", valueNameFirm, delegatePageList.get());
        } catch (Exception e) {
            log.error("Запись c фильтрои {} не найдена {}", valueNameFirm, e.getMessage());
//            throw new IOException("Запись c фильтрои {} не найдена {}");
        }
        return delegatePageList;
    }

    public DelegateDtoResponse createModelName(DelegateDtoRequest delegateDtoRequest) {
        log.info("Создаем нового существующего делегата {}", delegateDtoRequest);
        DelegateDtoResponse delegateDtoResponse = new DelegateDtoResponse();
        Delegate delegate = new Delegate();
        log.info("inizialization complete delegateDtoResponce {} -isNull {} & delegate {} isNull {}", delegateDtoResponse, delegateDtoResponse != null, delegate, delegate != null);
        DelegateMapper.INSTANCE.mapFromDtoReq(delegate, delegateDtoRequest);
        delegate.setDataCreate(ZonedDateTime.now());
        log.info("update delegate fromDTORequest {}", delegate);
        Delegate save = delegateRepository.save(delegate);
        log.info("save to DB delegate-save {}", save);
//        delegate.setDataCreate(ZonedDateTime.now());
        DelegateMapper.INSTANCE.mapToDtoResp(delegateDtoResponse, save);
        log.info("update DTORepsonce {}", delegateDtoResponse);
        return delegateDtoResponse;
    }

    public DelegateDtoResponse updateData(Long id, DelegateDtoRequest delegateDtoRequest) throws IOException {
        Optional<Delegate> foundDelegateDtoById = delegateRepository.findById(id);
        DelegateDtoResponse delegateDtoResponse = new DelegateDtoResponse();
        if (foundDelegateDtoById.isPresent()) {
            Delegate delegate = foundDelegateDtoById.get();
            DelegateMapper.INSTANCE.mapFromDtoReq(delegate, delegateDtoRequest);
            Delegate save = delegateRepository.save(delegate);
            methodUnique(save);
            DelegateMapper.INSTANCE.mapToDtoResp(delegateDtoResponse, save);
            return delegateDtoResponse;
        }
        log.info("Запись с id={} в бд не найдена", id);
        throw new IOException("Запись с id={} в бд не найдена");
    }

    public void delete(Long id) throws IOException {
        Optional<Delegate> foundDelegateOptional = delegateRepository.findById(id);
        log.info("Информация о записи из бд {}", foundDelegateOptional);
        if (foundDelegateOptional.isPresent()) {
            delegateRepository.deleteById(id);
            log.info("Удалена из бд {}", foundDelegateOptional);
        } else {
            log.error("Запись id= {} не найдена в бд", id);
            throw new IOException("Запись с id " + id + " не найдена");
        }
    }

    private void methodUnique(Delegate delegateFromDB) {
        delegateRepository.findById(delegateFromDB.getId());
    }

}

