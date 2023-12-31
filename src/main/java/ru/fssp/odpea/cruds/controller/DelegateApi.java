package ru.fssp.odpea.cruds.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.dto.DelegateDtoResponse;
import ru.fssp.odpea.object.Delegate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Optional;

@Tag(name = "Справочник заполнения таблицы отчетности Делегаты")
@RequestMapping("/api/v1/delegate")
@ApiOperation("Products API")
public interface DelegateApi {
    @Operation(summary = "Сервис для отправления всех данных Делегаты на фронт с пагинацией и фильтром по полю организации, сдающей отчетность")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<Delegate>> getAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(value = "valueWho", required = false) String valueNameFirm);

    @Operation(summary = "Сервис обновления объекта Делегаты")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DelegateDtoResponse> update(
            @PathVariable Long id,
            @RequestBody DelegateDtoRequest aboInputDelegateDtoRequest);


    @Operation(summary = "Сервис удаления объекта Делегаты")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteAboInput(@PathVariable Long id);

    @Operation(summary = "Сервис для добавления объекта Делегаты")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Delegate> create(@RequestBody DelegateDtoRequest aboInputDelegateDtoRequest);
}