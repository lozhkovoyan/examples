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

    public ResponseEntity<Delegate> create(DelegateDtoRequest aboInputDelegateDtoRequest) {
        log.info("Получен запрос для добавления нового объекта {}", aboInputDelegateDtoRequest);
//        AboInputDelegate mappedAboInputDelegate = AboInputDelegateMapper.INSTANCE.mapFromDto(aboInputDelegateDto);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(delegateService.create(aboInputDelegateDtoRequest));
        } catch (Exception e) {
            log.error("Ошибка сохранения {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new Delegate());
        }
    }

    public ResponseEntity<Page<Delegate>> getAll(
            int page,
            int size,
            Optional<String> valueWho) {
        log.info("Получен запрос на получение всех значений показателя (OGRN|REGNOM) {} организации, сдающей отчетность", valueWho);
        Pageable pageable = PageRequest.of(page, size);
        log.info("Заполнены поля для пагинации(page {}, size {}), pageable {}", page, size, pageable);
        Page<Delegate> allDelegatesByValueWho = delegateService.findAllWithValueNameFirm(pageable, valueWho);
        if (allDelegatesByValueWho.isEmpty()) {
            log.info("Организации не найдены {} сдающей отчетность ", valueWho);
        }
        return ResponseEntity.status(HttpStatus.OK).body(allDelegatesByValueWho);
    }


    public ResponseEntity<DelegateDtoResponse> update(
            Long id,
            DelegateDtoRequest aboInputDelegateDtoRequest) {
        log.info("Получен запрос на обновление делегата id = {}, с полями: {}", id, aboInputDelegateDtoRequest);
        DelegateDtoResponse savedDelegate = new DelegateDtoResponse();
        try {
            savedDelegate = delegateService.updateData(id, aboInputDelegateDtoRequest);
            log.info("Получен ответ на обновление делегата id = {}, с полями: {}", id, savedDelegate);
            return ResponseEntity.status(HttpStatus.OK).body(savedDelegate);
        } catch (Exception e) {
            log.error("Ошибка обновления id= {}, {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(savedDelegate);
        }
    }

    @SneakyThrows
    public void deleteAboInput(Long id) {
        log.info("Запрос на удаление");
        try {
            delegateService.delete(id);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }
}