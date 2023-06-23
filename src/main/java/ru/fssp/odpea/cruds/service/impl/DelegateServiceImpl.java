package ru.fssp.odpea.cruds.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.dto.DelegateDtoResponce;
import ru.fssp.odpea.cruds.mapper.DelegateMapper;
import ru.fssp.odpea.cruds.model.Delegate;
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

//    public List<DelegateDto> findAll() {
//        return delegateRepository.findAll();
//    }

    public Page<Delegate> findAllWithValueNameFirm(Pageable pageable, String valueNameFirm) throws IOException {
        log.info("Сервис обработки вернуть всех делегатов с фильтром {} и пагинацией {} при наличии", valueNameFirm, pageable);
        Page<Delegate> all = null;
        try {
            Specification<Delegate> specification = DelegateSpecification.hasFilterValueWho(valueNameFirm);
            all = delegateRepository.findAll(specification, pageable);
            if (all.isEmpty()) {
                throw new IOException("all.isEmpty() - " + all.isEmpty());
            }
        } catch (Exception e) {
            log.error("Запись c фильтрои {} не найдена {}", valueNameFirm, e.getMessage());
//            throw new IOException("Запись c фильтрои {} не найдена {}");
        }
            return all;
    }

    //        DelegateDto modelNameResponse = DelegateMapper.INSTANCE.mapFromDto(savedDelegate);
    public DelegateDtoResponce createModelName(DelegateDtoRequest delegateDtoRequest) {
        log.info("Создаем нового существующего делегата {}", delegateDtoRequest);
        DelegateDtoResponce delegateDtoResponce = new DelegateDtoResponce();
        Delegate delegate = new Delegate();
        log.info("inizialization complete delegateDtoResponce {} -isNull {} & delegate {} isNull {}", delegateDtoResponce, delegateDtoResponce != null, delegate, delegate != null);
        DelegateMapper.INSTANCE.mapFromDtoReq(delegate, delegateDtoRequest);
        delegate.setDataCreate(ZonedDateTime.now());
        log.info("update delegate fromDTORequest {}", delegate);
        Delegate save = delegateRepository.save(delegate);
        log.info("save to DB delegate-save {}", save);
//        delegate.setDataCreate(ZonedDateTime.now());
        DelegateMapper.INSTANCE.mapToDtoResp(delegateDtoResponce, save);
        log.info("update DTORepsonce {}", delegateDtoResponce);
        return delegateDtoResponce;
    }

    //        DelegateDto modelNameResponse = DelegateMapper.INSTANCE.mapFromDto(savedDelegate);
    public DelegateDtoResponce updateData(Long id, DelegateDtoRequest delegateDtoRequest) throws IOException {
        Optional<Delegate> foundDelegateDtoById = delegateRepository.findById(id);
        DelegateDtoResponce delegateDtoResponce = new DelegateDtoResponce();
        if (foundDelegateDtoById.isPresent()) {
            Delegate delegate = foundDelegateDtoById.get();
            DelegateMapper.INSTANCE.mapFromDtoReq(delegate, delegateDtoRequest);
            Delegate save = delegateRepository.save(delegate);
            DelegateMapper.INSTANCE.mapToDtoResp(delegateDtoResponce, save);
//            Delegate mappedDelegate = foundDelegateDtoById.get();
//            DelegateDto delegateDtoFromRepositoryGet = foundDelegateDtoById.get();
/*            delegateDtoResponce.setType(delegateDtoRequest.getType());
            delegateDtoResponce.setValueNameFirm(delegateDtoRequest.getValueNameFirm());
            delegateDtoResponce.setValueInsteadNameFirm(delegateDtoRequest.getValueInsteadNameFirm());
            delegateDtoResponce.setDtBeg(delegateDtoRequest.getDtBeg());
            delegateDtoResponce.setDtEnd(delegateDtoRequest.getDtEnd());
            delegateDtoResponce.setIsNowActive(delegateDtoRequest.getIsNowActive());
            delegateDtoResponce.setUserCreate(delegateDtoRequest.getUserCreate());*/
            return delegateDtoResponce;
        }
        log.info("Запись с id={} в бд не найдена", id);
        throw new IOException("Запись с id={} в бд не найдена");
    }
}