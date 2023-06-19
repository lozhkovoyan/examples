package ru.fssp.odpea.cruds.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fssp.odpea.cruds.dto.ModelNameDto;
import ru.fssp.odpea.cruds.mapper.ModelNameMapper;
import ru.fssp.odpea.cruds.model.ModelName;
import ru.fssp.odpea.cruds.service.ModelInterface;

import java.util.List;

@ApiOperation("Products API")
@RequestMapping("/model/api1")
@RestController
@Slf4j
public class ModelController {// NamingPractice
    private final ModelInterface modelInterface;

    public ModelController(ModelInterface modelInterface) {
        this.modelInterface = modelInterface;
    }
    //Update endpoint
//1) модель поменять на ModelNameDTO
//2) Mapping - mapStruct доб-ть в зависимости,

    @PutMapping("/update/{id}")
    public ResponseEntity<ModelName> update(@PathVariable Long id, @RequestBody ModelName data) {
        ModelName savedData = modelInterface.updateData(id, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedData);
    }

    @PostMapping("/create")
    public ResponseEntity<ModelNameDto> create(@RequestBody ModelNameDto modelNameDto) { //DTO
        ModelName modelName = ModelNameMapper.INSTANCE.mapToDto(modelNameDto);

        ModelName savedModelName = modelInterface.createModelName(modelName);
        ModelNameDto modelNameResponse = ModelNameMapper.INSTANCE.mapFromDto(savedModelName);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelNameResponse);
    }

    @ApiOperation("Метод Гет")
    @GetMapping("/model")
    public ResponseEntity<List<ModelName>> getAllByValueNameFirm(Pageable pageable,
                                                                 @RequestParam String valueNameFirm) {
        List<ModelName> allByValueNameFirm = modelInterface.findAllByValueNameFirm(pageable, valueNameFirm);
        return ResponseEntity.status(HttpStatus.OK).body(allByValueNameFirm);
    }
}