package ru.fssp.odpea.cruds.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fssp.odpea.cruds.model.ModelName;
import ru.fssp.odpea.cruds.repository.ModelRepository;
import ru.fssp.odpea.cruds.service.ModelService;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ModelServiceImpl implements ModelService {

    private ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    public List<ModelName> findAll() {
        return modelRepository.findAll();
    }

    public Page<ModelName> findAllByValueNameFirm(Pageable pageable, String valueNameFirm) {
        Page<ModelName> page = modelRepository.findAllByValueNameFirm(valueNameFirm, pageable);
        return page;
    }

    public ModelName createModelName(ModelName modelName) {
        modelName.setDataCreate(ZonedDateTime.now());
        return modelRepository.save(modelName);
    }

    public ModelName updateData(Long id, ModelName data) {
        Optional<ModelName> foundModelNameByIdRepo = modelRepository.findById(id);
        if (foundModelNameByIdRepo.isPresent()) {
            ModelName modelNameFromRepositoryGet = foundModelNameByIdRepo.get();
            modelNameFromRepositoryGet.setType(data.getType());
            modelNameFromRepositoryGet.setValueNameFirm(data.getValueNameFirm());
            modelNameFromRepositoryGet.setValueInsteadNameFirm(data.getValueInsteadNameFirm());
            modelNameFromRepositoryGet.setDtBeg(data.getDtBeg());
            modelNameFromRepositoryGet.setDtEnd(data.getDtEnd());
            modelNameFromRepositoryGet.setIsNowActive(data.getIsNowActive());
            modelNameFromRepositoryGet.setUserCreate(data.getUserCreate());
            return modelRepository.save(modelNameFromRepositoryGet);
        }
        return null;
    }
}