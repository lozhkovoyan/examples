package ru.fssp.odpea;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class OdpeaApplication {
    public static void main(String[] args) {
        SpringApplication.run(OdpeaApplication.class, args);
    }
}

/*

DELEGATE – ентити сажаем не него.

ID INTEGER DEFAULT DELEGATE_SQ.nextval NOT NULL,
TYPE varchar2(30 BYTE) NOT NULL,
VALUE_NAME_FIRM varchar2(30 BYTE),
VALUE_INSTEAD_NAME_FIRM varchar2(30 BYTE),
DT_BEG DATE NOT NULL ENABLE,
DT_END DATE NOT NULL ENABLE,
IS_NOW_ACTIVE CHAR(1 BYTE) DEFAULT 'Y',
USER_CREATE VARCHAR2(255 BYTE) DEFAULT USER NOT NULL,
DATA_CREATE DATE DEFAULT SYSDATE NOT NULL

Описание полей:
ID  'Идентификатор записи';
TYPE  'Тип записи (FIRST|SECOND) ';
VALUE_NAME_FIRM  ' (TYPE) Название фирмы, сдающей отчет';
VALUE_INSTEAD_NAME_FIRM  'Значения показателя (TYPE) фирмы, за которую можно сдавать отчет';
DT_BEG  'Дата начала действия записи';
DT_END  'Дата окончания действия записи';
IS_NOW_ACTIVE  'Активность записи Y/N';
USER_CREATE  'User, создавший/отредактировавший запись';
DATA_CREATE  'Дата создания/редактирования записи';


1)

Create endpoint -
С фронта придёт всё кроме ID и DATA_CREATE. ID не заполняем – оракл сам его заполнит при инсерте. DATA_CREATE – заполняем в сервисе.

Read endpoint – Отдаём всё на фронт с пагинацией. Предусмотреть фильтр по VALUE_FIRM_NAME.

Update endpoint
С фронта придёт всё кроме DATA_CREATE. Обновляем по ID, DATA_CREATE – не трогаем.
/*

3) Сделать проверку на дубликат. Создание ровно такой же записи (цифры/дата/время)
или создание записи с одинаковыми реквизитами, но которая пересекается с
периодом начала/окончания уже существующей нельзя добавлять.
Выдаем сообщение с ошибкой создания "Данная запись уже существует" - для абсолютно идентичных данных.

4) Добавить кнопку удаления. Должно быть удаление записи из ЭФ и соответственно из БД
Закончил с логикой сервиса, начинаю тестирование

1. Цифры, дата и время совпадают
2. Реквизиты и дата начала ИЛИ реквизиты и дата окончания
*/