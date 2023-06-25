package ru.fssp.odpea.cruds.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class DelegateDtoRequest {
    //    enum
    private String type;
    private String valueNameFirm;
    private String valueInsteadNameFirm;
    private ZonedDateTime dtBeg;
    private ZonedDateTime dtEnd;
    private Character isNowActive;
    private String userCreate;
    private ZonedDateTime dataCreate;
}
