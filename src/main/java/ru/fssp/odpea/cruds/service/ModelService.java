package ru.fssp.odpea.cruds.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.fssp.odpea.cruds.model.ModelName;

import java.util.List;

public interface ModelService {
    List<ModelName> findAll();

    Page<ModelName> findAllByValueNameFirm(Pageable pageable, String valueNameFirm);

    ModelName createModelName(ModelName modelName);

    ModelName updateData(Long id, ModelName data);
}