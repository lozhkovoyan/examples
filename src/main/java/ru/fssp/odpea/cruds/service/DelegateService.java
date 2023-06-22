package ru.fssp.odpea.cruds.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.model.Delegate;

import java.io.IOException;

public interface DelegateService {
//    List<DelegateDto> findAll();

    Page<Delegate> findAllWithValueNameFirm(Pageable pageable, String valueNameFirm);

    DelegateDtoRequest createModelName(Delegate delegate);

    DelegateDtoRequest updateData(Long id, Delegate delegate) throws IOException;
}