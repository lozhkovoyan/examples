package ru.fssp.odpea.cruds.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fssp.odpea.cruds.dto.ModelNameDto;
import ru.fssp.odpea.cruds.mapper.DelegateMapper;
import ru.fssp.odpea.cruds.model.Delegate;
import ru.fssp.odpea.cruds.service.DelegateService;

@ApiOperation("Products API")
@RequestMapping("/model/api1")
@RestController
@Slf4j
public class DelegateController {// NamingPractice
    private final DelegateService delegateService;

    public DelegateController(DelegateService delegateService) {
        this.delegateService = delegateService;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ModelNameDto> update(@PathVariable Long id, @RequestBody ModelNameDto modelNameDto) {
        Delegate mappedDelegateDto = DelegateMapper.INSTANCE.mapToDto(modelNameDto);

        Delegate savedDelegate = delegateService.updateData(id, mappedDelegateDto);
        ModelNameDto modelNameResponse = DelegateMapper.INSTANCE.mapFromDto(savedDelegate);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelNameResponse);
    }

    @PostMapping("/create")
    public ResponseEntity<ModelNameDto> create(@RequestBody ModelNameDto modelNameDto) { //DTO
        Delegate mappedDelegateDto = DelegateMapper.INSTANCE.mapToDto(modelNameDto);

        Delegate savedDelegate = delegateService.createModelName(mappedDelegateDto);
        ModelNameDto modelNameResponse = DelegateMapper.INSTANCE.mapFromDto(savedDelegate);

        return ResponseEntity.status(HttpStatus.CREATED).body(modelNameResponse);
    }

    @ApiOperation("Метод Гет")
    @GetMapping("/model")
    public ResponseEntity<Page<Delegate>> getAllByValueNameFirm(Pageable pageable,
                                                                @RequestParam String valueNameFirm) {
        Page<Delegate> allByValueNameFirm = delegateService.findAllByValueNameFirm(pageable, valueNameFirm);
        return ResponseEntity.status(HttpStatus.OK).body(allByValueNameFirm);
    }
}