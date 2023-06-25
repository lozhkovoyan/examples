package ru.fssp.odpea.cruds.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.dto.DelegateDtoResponse;
import ru.fssp.odpea.object.Delegate;
import ru.fssp.odpea.cruds.service.impl.DelegateServiceImpl;

import java.io.IOException;
import java.util.Optional;

@ApiOperation("Products API")
@RequestMapping("/api/v1/delegate")
@RestController
@Slf4j
public class DelegateController {// NamingPractice
    private final DelegateServiceImpl delegateService;

    public DelegateController(DelegateServiceImpl delegateService) {
        this.delegateService = delegateService;
    }

    @Operation(summary = "Сервис для обновления всех данных Делегата")
    @PutMapping("/{id}")
    public ResponseEntity<DelegateDtoResponse> updateDelegate(@PathVariable Long id,
                                                              @RequestBody DelegateDtoRequest delegate) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(delegateService.updateData(id, delegate));

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Сервис создания Делегата организации")
    @PostMapping("/create")
    public ResponseEntity<DelegateDtoResponse> createDelegate(@RequestBody DelegateDtoRequest delegateDtoRequest) { //DTO
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(delegateService.createModelName(delegateDtoRequest));
    }

    @Operation(summary = "Сервис для отправления всех данных Delegate на фронт с пагинацией и фильтром по полю значения показателя (FIRST|SECOND) организации, сдающей отчетность")
    @GetMapping("/get")
    public ResponseEntity<Page<Delegate>> getAllDelegate(
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(value = "valueNameFirm", required = false) Optional<String> valueNameFirm) {
        log.info("Получен запрос на получение всех значений показателя (FIRST|SECOND) {} организации, сдающей отчетность page {}, size {}",
                valueNameFirm, page, size);
        Pageable pageable = PageRequest.of(page, size);
        log.info("Обогощенный класс пагинации {}", pageable);
        Page<Delegate> allDelegatesByvalueNameFirm = delegateService.findAllWithValueNameFirm(pageable, valueNameFirm);
        if (allDelegatesByvalueNameFirm.isEmpty()) {
            log.info("Организации не найдены {} сдающей отчетность ", valueNameFirm);
        }
        return ResponseEntity.status(HttpStatus.OK).body(allDelegatesByvalueNameFirm);
    }

    @SneakyThrows
    @Operation(summary = "Сервис удаления объекта Delegate если существует")
    @DeleteMapping("/delete/{id}")
    public void deleteAboInput(@PathVariable Long id) {
        log.info("Запрос на удаление");
        try {
            delegateService.delete(id);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}