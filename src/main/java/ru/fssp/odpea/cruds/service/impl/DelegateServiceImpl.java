package ru.fssp.odpea.cruds.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fssp.odpea.cruds.model.Delegate;
import ru.fssp.odpea.cruds.repository.DelegateRepository;
import ru.fssp.odpea.cruds.service.DelegateService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DelegateServiceImpl implements DelegateService {

    private DelegateRepository delegateRepository;

    public DelegateServiceImpl(DelegateRepository delegateRepository) {
        this.delegateRepository = delegateRepository;
    }

    public List<Delegate> findAll() {
        return delegateRepository.findAll();
    }

    public Page<Delegate> findAllByValueNameFirm(Pageable pageable, String valueNameFirm) {
        Page<Delegate> page = delegateRepository.findAllByValueNameFirm(valueNameFirm, pageable);
        return page;
    }

    public Delegate createModelName(Delegate delegate) {
        delegate.setDataCreate(ZonedDateTime.now());
        return delegateRepository.save(delegate);
    }

    public Delegate updateData(Long id, Delegate data) {
        Optional<Delegate> foundModelNameByIdRepo = delegateRepository.findById(id);
        if (foundModelNameByIdRepo.isPresent()) {
            Delegate delegateFromRepositoryGet = foundModelNameByIdRepo.get();
            delegateFromRepositoryGet.setType(data.getType());
            delegateFromRepositoryGet.setValueNameFirm(data.getValueNameFirm());
            delegateFromRepositoryGet.setValueInsteadNameFirm(data.getValueInsteadNameFirm());
            delegateFromRepositoryGet.setDtBeg(data.getDtBeg());
            delegateFromRepositoryGet.setDtEnd(data.getDtEnd());
            delegateFromRepositoryGet.setIsNowActive(data.getIsNowActive());
            delegateFromRepositoryGet.setUserCreate(data.getUserCreate());
            return delegateRepository.save(delegateFromRepositoryGet);
        }
        return null;
    }
}