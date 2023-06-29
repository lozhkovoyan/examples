package ru.fssp.odpea.cruds.dto;

import lombok.Data;
import ru.fssp.odpea.object.Type;

import java.time.LocalDateTime;

@Data
public class DelegateDtoRequest {
    //    enum
    private Type type;
    private String valueNameFirm;
    private String valueInsteadNameFirm;
    private LocalDateTime dtBeg;
    private LocalDateTime dtEnd;
    private Character isNowActive;
    private String userCreate;
    private LocalDateTime dataCreate;
}
