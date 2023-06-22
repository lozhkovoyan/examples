package ru.fssp.odpea.cruds.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.mapper.DelegateMapper;
import ru.fssp.odpea.cruds.model.Delegate;
import ru.fssp.odpea.cruds.repository.DelegateRepository;
import ru.fssp.odpea.cruds.service.DelegateService;
import ru.fssp.odpea.cruds.service.DelegateSpecification;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@Slf4j
public class DelegateServiceImpl implements DelegateService {

    private final DelegateRepository delegateRepository;

    public DelegateServiceImpl(DelegateRepository delegateRepository) {
        this.delegateRepository = delegateRepository;
    }

//    public List<DelegateDto> findAll() {
//        return delegateRepository.findAll();
//    }

    public Page<Delegate> findAllWithValueNameFirm(Pageable pageable, String valueNameFirm) {
        log.info("Сервис обработки вернуть всех делегатов с фильтром {} и пагинацией {} при наличии", valueNameFirm, pageable);
        Specification<Delegate> specification = DelegateSpecification.hasFilterValueWho(valueNameFirm);
        Page<Delegate> all = delegateRepository.findAll(specification, pageable);
        return all;
    }

    //        DelegateDto modelNameResponse = DelegateMapper.INSTANCE.mapFromDto(savedDelegate);
    public DelegateDtoRequest createModelName(Delegate delegate) {
//        DelegateDto mappedDelegateDto = DelegateMapper.INSTANCE.mapToDto(delegate);
        delegate.setDataCreate(ZonedDateTime.now());
        return DelegateMapper.INSTANCE.mapToDto(delegateRepository.save(delegate));
    }

    //        DelegateDto modelNameResponse = DelegateMapper.INSTANCE.mapFromDto(savedDelegate);
    public DelegateDtoRequest updateData(Long id, Delegate delegate) throws IOException {
        Optional<Delegate> foundDelegateDtoById = delegateRepository.findById(id);
        if (foundDelegateDtoById.isPresent()) {
            Delegate mappedDelegate = foundDelegateDtoById.get();
//            DelegateDto delegateDtoFromRepositoryGet = foundDelegateDtoById.get();
            mappedDelegate.setType(delegate.getType());
            mappedDelegate.setValueNameFirm(delegate.getValueNameFirm());
            mappedDelegate.setValueInsteadNameFirm(delegate.getValueInsteadNameFirm());
            mappedDelegate.setDtBeg(delegate.getDtBeg());
            mappedDelegate.setDtEnd(delegate.getDtEnd());
            mappedDelegate.setIsNowActive(delegate.getIsNowActive());
            mappedDelegate.setUserCreate(delegate.getUserCreate());
            return DelegateMapper.INSTANCE.mapToDto(delegateRepository.save(mappedDelegate));
        }
        log.info("Запись с id={} в бд не найдена", id);
        throw new IOException("Запись с id={} в бд не найдена");
    }
}