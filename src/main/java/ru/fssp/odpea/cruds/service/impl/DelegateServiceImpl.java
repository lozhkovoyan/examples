package ru.fssp.odpea.cruds.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fssp.odpea.cruds.dto.DelegateDto;
import ru.fssp.odpea.cruds.mapper.DelegateMapper;
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

//    public List<Delegate> findAll() {
//        return delegateRepository.findAll();
//    }

    public Page<DelegateDto> findAllByValueNameFirm(Pageable pageable, String valueNameFirm) {
        Page<DelegateDto> page = delegateRepository.findAllByValueNameFirm(valueNameFirm, pageable);
        return page;
    }

//        DelegateDto modelNameResponse = DelegateMapper.INSTANCE.mapFromDto(savedDelegate);
    public DelegateDto createModelName(Delegate delegate) {
        DelegateDto mappedDelegateDto = DelegateMapper.INSTANCE.mapToDto(delegate);
        mappedDelegateDto.setDataCreate(ZonedDateTime.now());
        return delegateRepository.save(mappedDelegateDto);
    }

//        DelegateDto modelNameResponse = DelegateMapper.INSTANCE.mapFromDto(savedDelegate);
    public DelegateDto updateData(Long id, DelegateDto delegateDto) {
//        Delegate mappedDelegateDto = DelegateMapper.INSTANCE.mapToDto(delegateDto);
        Optional<DelegateDto> foundDelegateDtoById = delegateRepository.findById(id);
        if (foundDelegateDtoById.isPresent()) {
            DelegateDto delegateDtoFromRepositoryGet = foundDelegateDtoById.get();
            delegateDtoFromRepositoryGet.setType(delegateDto.getType());
            delegateDtoFromRepositoryGet.setValueNameFirm(delegateDto.getValueNameFirm());
            delegateDtoFromRepositoryGet.setValueInsteadNameFirm(delegateDto.getValueInsteadNameFirm());
            delegateDtoFromRepositoryGet.setDtBeg(delegateDto.getDtBeg());
            delegateDtoFromRepositoryGet.setDtEnd(delegateDto.getDtEnd());
            delegateDtoFromRepositoryGet.setIsNowActive(delegateDto.getIsNowActive());
            delegateDtoFromRepositoryGet.setUserCreate(delegateDto.getUserCreate());
            return delegateRepository.save(delegateDtoFromRepositoryGet);
        }
        return null;
    }
}