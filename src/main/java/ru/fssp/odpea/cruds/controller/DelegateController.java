package ru.fssp.odpea.cruds.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fssp.odpea.cruds.dto.DelegateDto;
import ru.fssp.odpea.cruds.model.Delegate;
import ru.fssp.odpea.cruds.service.DelegateService;

@ApiOperation("Products API")
@RequestMapping("/delegate/api1")
@RestController
@Slf4j
public class DelegateController {// NamingPractice
    private final DelegateService delegateService;

    public DelegateController(DelegateService delegateService) {
        this.delegateService = delegateService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DelegateDto> updateDelegate(@PathVariable Long id,
                                                      @RequestBody DelegateDto delegateDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(delegateService.updateData(id, delegateDto));
    }

    @PostMapping("/create")
    public ResponseEntity<DelegateDto> createDelegate(@RequestBody Delegate delegate) { //DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(delegateService.createModelName(delegate));
    }

    @ApiOperation("Метод Гет")
    @GetMapping("/get")
    public ResponseEntity<Page<DelegateDto>> getAllDelegateByValueNameFirm(Pageable pageable,
                                                                           @RequestParam String valueNameFirm) {
        Page<DelegateDto> allByValueNameFirm = delegateService.findAllByValueNameFirm(pageable, valueNameFirm);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(allByValueNameFirm);
    }
}