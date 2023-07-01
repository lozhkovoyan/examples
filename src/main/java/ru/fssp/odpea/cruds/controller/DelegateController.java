package ru.fssp.odpea.cruds.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.dto.DelegateDtoResponse;
import ru.fssp.odpea.cruds.exceptions.DelegateException;
import ru.fssp.odpea.object.Delegate;
import ru.fssp.odpea.cruds.service.DelegateService;

import java.util.Objects;


@RestController
@Slf4j
public class DelegateController implements DelegateApi {// NamingPractice
    private final DelegateService delegateService;
    public DelegateController(DelegateService delegateService) {
        this.delegateService = delegateService;
    }

    public ResponseEntity<Delegate> create(DelegateDtoRequest aboInputDelegateDtoRequest) {
        log.info("Получен запрос для добавления нового объекта {}", aboInputDelegateDtoRequest);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(delegateService.create(aboInputDelegateDtoRequest));
        } catch (Exception e) {
            log.error("Ошибка сохранения {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Delegate());
        }
    }

    public ResponseEntity<Page<Delegate>> getAll(
            int page,
            int size,
            @Nullable String valueNameFirm) {
        log.info("Получен запрос на получение всех значений показателя (OGRN|REGNOM) {} организации, сдающей отчетность", valueNameFirm);
        Pageable pageable = PageRequest.of(page, size);
        log.info("Заполнены поля для пагинации(page {}, size {}), pageable {}", page, size, pageable);
        Page<Delegate> allDelegatesByValueWho = delegateService.findAllWithFilterValue(pageable, valueNameFirm);
        if (Objects.isNull(allDelegatesByValueWho) || allDelegatesByValueWho.isEmpty()) {
            log.info("Организации не найдены {} сдающей отчетность ", valueNameFirm);
        }
        return ResponseEntity.status(HttpStatus.OK).body(allDelegatesByValueWho);
    }


    public ResponseEntity<DelegateDtoResponse> update(
            Long id,
            DelegateDtoRequest aboInputDelegateDtoRequest) {
        log.info("Получен запрос на обновление делегата id = {}, с полями: {}", id, aboInputDelegateDtoRequest);
        DelegateDtoResponse savedDelegate = new DelegateDtoResponse();
        try {
            savedDelegate = delegateService.update(id, aboInputDelegateDtoRequest);
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
        } catch (DelegateException e) {
            log.info(e.getMessage());
        }
    }
}