package ru.fssp.odpea.cruds.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.fssp.odpea.cruds.model.Delegate;

import java.util.List;

public interface DelegateService {
    List<Delegate> findAll();

    Page<Delegate> findAllByValueNameFirm(Pageable pageable, String valueNameFirm);

    Delegate createModelName(Delegate delegate);

    Delegate updateData(Long id, Delegate data);
}