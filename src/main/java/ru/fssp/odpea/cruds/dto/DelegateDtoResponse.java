package ru.fssp.odpea.cruds.dto;

import lombok.Data;
import ru.fssp.odpea.object.Type;

import java.time.ZonedDateTime;
@Data
public class DelegateDtoResponse {
    private Long id;
    private Type type;
    private String valueNameFirm;
    private String valueInsteadNameFirm;
    private ZonedDateTime dtBeg;
    private ZonedDateTime dtEnd;
    private Character isNowActive;
    private String userCreate;
    private ZonedDateTime dataCreate;
}
