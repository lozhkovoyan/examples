package ru.fssp.odpea.cruds.service.impl;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.dto.DelegateDtoResponse;
import ru.fssp.odpea.cruds.exceptions.DuplicateReportException;
import ru.fssp.odpea.cruds.mapper.DelegateMapper;
import ru.fssp.odpea.object.Delegate;
import ru.fssp.odpea.cruds.repository.DelegateRepository;
import ru.fssp.odpea.cruds.service.DelegateSpecification;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static ru.fssp.odpea.cruds.service.DelegateSpecification.hasDublicates;

@Service
@Slf4j
public class DelegateServiceImpl {

    private final DelegateRepository delegateRepository;

    public DelegateServiceImpl(DelegateRepository delegateRepository) {
        this.delegateRepository = delegateRepository;
    }


    private void methodUnique(Delegate delegateFromDB) {
        delegateRepository.findById(delegateFromDB.getId());
    }
    @SneakyThrows
    public Delegate create(DelegateDtoRequest aboInputDelegateRqst) {
        log.info("Сервис обработки добавления нового объекта делегата {}", aboInputDelegateRqst);
        Delegate delegate = new Delegate();
        checkForDublicates(null, aboInputDelegateRqst);
        if (aboInputDelegateRqst.getDtBeg().isBefore(aboInputDelegateRqst.getDtEnd())) {
            log.info("Дата начала записи соответствует требованию с датой окончания записи {}, {}", aboInputDelegateRqst.getDataCreate(), aboInputDelegateRqst);
            try {
                log.info("Делегат, установлена дата {}", delegate);
                DelegateMapper.INSTANCE.mapFromDtoReq(delegate, aboInputDelegateRqst);
                delegate.setDataCreate(ZonedDateTime.now());
                return delegateRepository.save(delegate);
            } catch (Exception e) {
                log.error("Сохранение не удалось {}", e.getMessage());
                throw new IOException(e.getMessage(), e);
            }
        }
        log.error("Дата начала записи {} не должна быть позже, чем дата окончания записи {}", aboInputDelegateRqst.getDtBeg(), aboInputDelegateRqst.getDtEnd());
        throw new IOException("Ошибка при попытке сохранить несовместимые даты");
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
        Delegate delegate = new Delegate();
        DelegateMapper.INSTANCE.mapFromDtoReq(delegate, aboInputDelegateRqst);
        if (id != null) {
            delegate.setId(id);
        }
        /*Specification<AboInputDelegate> specification = Specification.where(
                aboInputDelegateRqst == null ? null : hasDublicates(delegate));*/
//                aboInputDelegateRqst.getValueWho() == null ? null : fieldEquals(aboInputDelegateRqst.getValueWho()));
//        hasDublicates(delegate);
        Specification<Delegate> specification = hasDublicates(delegate);
//        Specification<Delegate> specification = DelegateSpecification::hasDublicates;
        log.info("Спецификация выполнена {}", specification);
        boolean exists = delegateRepository.exists(specification);
                /*delegate.getValueWho(),
                delegate.getValueForWhom(),
                delegate.getDtBeg(),
                delegate.getDtEnd(),
                delegate.getDType(),
                delegate.getIsNowActive())*/
        log.error("Данная запись уже существует {}", delegate);
        throw new DuplicateReportException("Данная запись уже существует");
//            }
        log.info("1-я проверка на дубликаты пройдена");
        checkForDublicatesAndDates(delegate);
        log.info("2-я проверка на пересечение дат на дубликаты пройдена");
    }


    /*создание записи с одинаковыми реквизитами, но которая пересекается с периодом начала/окончания уже существующей нельзя добавлять.*/
    private void checkForDublicatesAndDates (Delegate delegate){
        List<Delegate> existingRecordsDelegates = delegateRepository.findByValueWho(delegate.getValueNameFirm());
        log.info("Найденный список записей: {}", existingRecordsDelegates);
        /*if (delegateRepository.existsByValueWhoOrValueForWhomWithPeriod(
                delegate.getValueWho(),
                delegate.getValueForWhom(),
                delegate.getDtBeg(),
                delegate.getDtEnd())) {*/
        for (Delegate aboDelegate : existingRecordsDelegates) {
            if ((aboDelegate.getId() != delegate.getId()) | (aboDelegate.getId() == null)) {
                log.info("Условие исключения просмотра текущего изменяемого делегата: {}, {}, условие с equals: {}",
                        aboDelegate.getId(), delegate.getId(), !(aboDelegate.getId() == (delegate.getId())));
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
                    throw new DuplicateReportException("Данная запись уже существует");
                }
            }
        }
        log.error("Проверка пройдена корректно, совпадений не найдено");
    }

    public void delete (Long id) throws IOException {
        Optional<Delegate> foundDelegateOptional = delegateRepository.findById(id);
        log.info("Информация о записи из бд {}", foundDelegateOptional);
        if (foundDelegateOptional.isPresent()) {
            delegateRepository.deleteById(id);
            log.info("Удалена из бд {}", foundDelegateOptional);
        } else {
            log.error("Запись id= {} не найдена в бд", id);
            throw new IOException("Запись с id " + id + " не найдена");
        }
    }
}

