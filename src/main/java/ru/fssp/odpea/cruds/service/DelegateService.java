package ru.fssp.odpea.cruds.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.fssp.odpea.cruds.dto.DelegateDto;
import ru.fssp.odpea.cruds.model.Delegate;

import java.util.List;

public interface DelegateService {
//    List<Delegate> findAll();

    Page<DelegateDto> findAllByValueNameFirm(Pageable pageable, String valueNameFirm);

    DelegateDto createModelName(Delegate delegate);

    DelegateDto updateData(Long id, DelegateDto delegateDto);
}