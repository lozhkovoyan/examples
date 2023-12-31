package ru.fssp.odpea.cruds.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.dto.DelegateDtoResponse;
import ru.fssp.odpea.cruds.exceptions.DelegateException;
import ru.fssp.odpea.cruds.mapper.DelegateMapper;
import ru.fssp.odpea.cruds.repository.DelegateRepository;
import ru.fssp.odpea.cruds.specs.DelegateSpecification;
import ru.fssp.odpea.object.Delegate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DelegateService {

    private final DelegateRepository delegateRepository;

    public DelegateService(DelegateRepository delegateRepository) {
        this.delegateRepository = delegateRepository;
    }
// должны ли быть публичные методы сервиса???
    public Page<Delegate> findAllWithFilterValue(Pageable pageable, String valueNameFirm) {
        log.info("Сервис обработки поиска по значению показателя (OGRN|REGNOM) {} организации, сдающей отчетность", valueNameFirm);
        Page<Delegate> pageList = null;
        Specification<Delegate> specification = DelegateSpecification.getFilterValueNameFirm(valueNameFirm);
        log.info("Пагинация valueNameFirm {}, полученный запрос {}", valueNameFirm, specification);
        try {
            pageList = delegateRepository.findAll(specification, pageable);
            log.info("Получен список из бд с пагинацией valueNameFirm {} с полученный запрос {}", valueNameFirm, pageList.get());
        } catch (DataAccessException exception) {
            log.info("Ошибка получения списока из бд с пагинацией pageList {}", pageList);
        }
        return pageList;
    }

    @SneakyThrows
    public Delegate create(DelegateDtoRequest aboInputDelegateRqst) {
        log.info("Сервис обработки добавления нового объекта делегата {}", aboInputDelegateRqst);
        Delegate delegate = new Delegate();
        checkForDublicates(null, aboInputDelegateRqst);
        log.info("Дата создания записи из запроса= {}", aboInputDelegateRqst.getDataCreate());
        DelegateMapper.INSTANCE.mapFromDtoReq(delegate, aboInputDelegateRqst);
        delegate.setDataCreate(LocalDateTime.now());
        log.info("Делегат, установлена дата {}", delegate);
        return delegateRepository.save(delegate); // TODO: 01.07.2023  - нунжо ли отлавливать ошибки из бд проверяемые/непровер
        
//        log.error("Сохранение не удалось {}", delegate);
//                throw new IOException(e.getMessage(), e)
}

    public DelegateDtoResponse update(Long id, DelegateDtoRequest aboInputDelegateRqst) throws IOException {
        log.info("Сервис обработки обновление существующего делегата с id={} и обновляемыми параметрами {}", id, aboInputDelegateRqst);
        checkForDublicates(id, aboInputDelegateRqst);
        Optional<Delegate> foundDelegateDtoById = delegateRepository.findById(id);
        log.info("Из бд получен делегат с id={} и обновляемыми параметрами {}", id, foundDelegateDtoById);
        DelegateDtoResponse delegateDtoResponse = new DelegateDtoResponse();
        if (foundDelegateDtoById.isPresent()) {
            log.info("Полученный делегат не пустой");
            Delegate delegate = foundDelegateDtoById.get();
            aboInputDelegateRqst.setDataCreate(delegate.getDataCreate());
            log.info("Полученный делегат foundDelegateDtoById.get() {}", delegate);
            DelegateMapper.INSTANCE.mapFromDtoReq(delegate, aboInputDelegateRqst);
            log.info("Полученный делегат INSTANCE.mapFromDtoReq {}", delegate);
//            delegate.setDateCreate();
            Delegate save = delegateRepository.save(delegate);
            log.info("Dелегат сохранен в бд {}", save);
            DelegateMapper.INSTANCE.mapToDtoResp(delegateDtoResponse, save);
            delegateDtoResponse.setDataCreate(save.getDataCreate());
            log.info("Маппинг в дтоРеспонс {}", delegateDtoResponse);
            return delegateDtoResponse;
        }
        log.info("Ошибка обновления, делегат с id={} не найден", id);
        throw new IOException("Ошибка обновления");
    }

    //    не должна выдаваться ошибка, если обнеовление записи только по полям type & isActive
    private void checkForDublicates(Long id, DelegateDtoRequest aboInputDelegateRqst) {
        if (aboInputDelegateRqst.getDtBeg().isBefore(aboInputDelegateRqst.getDtEnd())) {
            Delegate delegate = new Delegate();
            DelegateMapper.INSTANCE.mapFromDtoReq(delegate, aboInputDelegateRqst);
            if (id != null) {
                delegate.setId(id);
            }
//вынести
            List<Delegate> allDelegate = delegateRepository.findAll(getDelegateSpecification(delegate))
                    .stream().filter(duvleDelegate -> !duvleDelegate.getId().equals(id))
                    .collect(Collectors.toList());
            log.error("Все записи дублекаты делегатов {}", allDelegate);
//        allDelegate.removeIf(duvleDelegate -> duvleDelegate.getId().equals(id));
            if (!allDelegate.isEmpty()) {
                log.error("Данная запись уже существует {}", allDelegate);
                throw new DelegateException("Данная запись уже существует");
            }
            log.info("Проверка на дубликаты пройдена");
        }
        log.error("Дата начала записи {} не должна быть позже, чем дата окончания записи {}", aboInputDelegateRqst.getDtBeg(), aboInputDelegateRqst.getDtEnd());
        throw new DelegateException("Ошибка при попытке сохранить несовместимые даты");
    }

    private static Specification<Delegate> getDelegateSpecification(Delegate delegate) {
        Specification<Delegate> specification = DelegateSpecification.getDublicates(
                delegate.getValueNameFirm(),
                delegate.getValueInsteadNameFirm(),
                delegate.getDtBeg(),
                delegate.getDtEnd(),
                delegate.getType(),
                delegate.getIsNowActive());
        log.info("Спецификация выполнена {}", specification);
        return specification;
    }

    /*создание записи с одинаковыми реквизитами, но которая пересекается с периодом начала/окончания уже существующей нельзя добавлять.*/
   /* private void checkForDublicatesAndDates(Delegate delegate) {
        List<Delegate> existingRecordsDelegates = delegateRepository.findByValueNameFirm(delegate.getValueNameFirm());
        log.info("Найденный список записей: {}", existingRecordsDelegates);
        *//*if (delegateRepository.existsByValueWhoOrValueForWhomWithPeriod(
                delegate.getValueWho(),
                delegate.getValueForWhom(),
                delegate.getDtBeg(),
                delegate.getDtEnd())) {*//*
        for (Delegate aboDelegate : existingRecordsDelegates) {
            if (Objects.equals(aboDelegate.getId(), delegate.getId()) || aboDelegate.getId() == null) {
                log.info("Условие исключения просмотра текущего изменяемого делегата: {}, {}, условие с equals: {}", aboDelegate.getId(), delegate.getId(), !aboDelegate.getId().equals(delegate.getId()));
                if (aboDelegate.getDtBeg().isBefore(delegate.getDtEnd())
                        && aboDelegate.getDtEnd().isAfter(delegate.getDtBeg())) {
                    log.error("Данная запись или данный временной промежуток уже существует, найденный список делегатов {}", existingRecordsDelegates);
                    log.error("Условие 1-е {} isBefore {} -{}, условие 2-е {} isAfter {} -{}",
                            aboDelegate.getDtBeg(),
                            delegate.getDtEnd(),
                            aboDelegate.getDtBeg().isBefore(delegate.getDtEnd()),
                            aboDelegate.getDtEnd(),
                            delegate.getDtBeg(),
                            aboDelegate.getDtEnd().isAfter(delegate.getDtBeg()));
                    throw new DelegateException("Данная запись уже существует");
                }
            }
        }
        log.error("Проверка пройдена корректно, совпадений не найдено");
    }*/

    public void delete(Long id) {
        Optional<Delegate> foundDelegateOptional = delegateRepository.findById(id);
        log.info("Информация о записи из бд {}", foundDelegateOptional);
        if (foundDelegateOptional.isPresent()) {
            delegateRepository.deleteById(id);
            log.info("Удалена из бд {}", foundDelegateOptional);
        } else {
            log.error("Запись id= {} не найдена в бд", id);
            throw new DelegateException("Запись с id " + id + " не найдена");
        }
    }
}
/*"1) Сделать возможность корректировать "Тип" у записи в таблице делегатов

 1) Сделать возможность изменять "Активность" у записи в таблице делегатов"
 "Сделать проверку на дубликат. Создание ровно такой же записи (цифры/дата/время)
 или создание записи с одинаковыми реквизитами, но которая пересекается с периодом
 начала/окончания уже существующей нельзя добавлять.
 Выдаем сообщение с ошибкой создания "Данная запись уже существует" -
 для абсолютно идентичных данных."*/

