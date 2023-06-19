package ru.fssp.odpea.cruds.dto;

import javax.persistence.Column;
import java.time.ZonedDateTime;

public class DTOModelName {
    private String type;
    private String valueNameFirm;
    private String valueIsteadNameFirm;
    private ZonedDateTime dtBeg;
    private ZonedDateTime dtEnd;
    private Character isNowActive;
    private String userCreate;
    private ZonedDateTime dataCreate;
}
