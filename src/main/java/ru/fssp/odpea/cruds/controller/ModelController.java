package ru.fssp.odpea.cruds.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fssp.odpea.cruds.dto.ModelNameDto;
import ru.fssp.odpea.cruds.mapper.ModelNameMapper;
import ru.fssp.odpea.cruds.model.ModelName;
import ru.fssp.odpea.cruds.service.ModelService;

@ApiOperation("Products API")
@RequestMapping("/model/api1")
@RestController
@Slf4j
public class ModelController {// NamingPractice
    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }
    //Update endpoint
//1) модель поменять на ModelNameDTO

    @PutMapping("/update/{id}")
    public ResponseEntity<ModelNameDto> update(@PathVariable Long id, @RequestBody ModelNameDto modelNameDto) {
        ModelName mappedModelNameDto = ModelNameMapper.INSTANCE.mapToDto(modelNameDto);

        ModelName savedModelName = modelService.updateData(id, mappedModelNameDto);
        ModelNameDto modelNameResponse = ModelNameMapper.INSTANCE.mapFromDto(savedModelName);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelNameResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ModelNameDto> create(@RequestBody ModelNameDto modelNameDto) { //DTO
        ModelName mappedModelNameDto = ModelNameMapper.INSTANCE.mapToDto(modelNameDto);

        ModelName savedModelName = modelService.createModelName(mappedModelNameDto);
        ModelNameDto modelNameResponse = ModelNameMapper.INSTANCE.mapFromDto(savedModelName);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelNameResponse);
    }

    @ApiOperation("Метод Гет")
    @GetMapping("/model")
    public ResponseEntity<Page<ModelName>> getAllByValueNameFirm(Pageable pageable,
                                                                 @RequestParam String valueNameFirm) {
        Page<ModelName> allByValueNameFirm = modelService.findAllByValueNameFirm(pageable, valueNameFirm);
        return ResponseEntity.status(HttpStatus.OK).body(allByValueNameFirm);
    }
}