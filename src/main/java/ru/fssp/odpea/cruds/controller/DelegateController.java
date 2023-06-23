package ru.fssp.odpea.cruds.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.model.Delegate;
import ru.fssp.odpea.cruds.service.DelegateService;

import java.io.IOException;

@ApiOperation("Products API")
@RequestMapping("/api/v1/delegate")
@RestController
@Slf4j
public class DelegateController {// NamingPractice
    private final DelegateService delegateService;

    public DelegateController(DelegateService delegateService) {
        this.delegateService = delegateService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DelegateDtoRequest> updateDelegate(@PathVariable Long id,
                                                      @RequestBody Delegate delegate) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(delegateService.updateData(id, delegate));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<DelegateDtoRequest> createDelegate(@RequestBody Delegate delegate) { //DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(delegateService.createModelName(delegate));
    }

    @Operation(summary = "Сервис для отправления всех данных Delegate на фронт с пагинацией и фильтром по полю значения показателя (FIRST|SECOND) организации, сдающей отчетность")
    @GetMapping("/get")
    public ResponseEntity<Page<Delegate>> getAllDelegate(
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) Integer page,
            @RequestParam String valueNameFirm) {
        log.info("Получен запрос на получение всех значений показателя (FIRST|SECOND) {} организации, сдающей отчетность page {}, size {}", valueNameFirm, page, size);
        try {
        Pageable pageable = PageRequest.of(page, size);
        log.info("Обогощенный класс пагинации {}", pageable);
        Page<Delegate> allDelegatesByValueWho = delegateService.findAllWithValueNameFirm(pageable, valueNameFirm);
            return ResponseEntity.status(HttpStatus.OK).body(allDelegatesByValueWho);
        } catch (Exception e) {
            log.error("Ошибка получения всех значений показателя организации {} сдающей отчетность ", valueNameFirm);
            return (ResponseEntity<Page<Delegate>>) ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
    }
}