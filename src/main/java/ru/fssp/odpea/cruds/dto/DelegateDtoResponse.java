package ru.fssp.odpea.cruds.dto;

import lombok.Data;
import ru.fssp.odpea.object.Type;

import java.time.LocalDateTime;

@Data
public class DelegateDtoResponse {
    private Long id;
    private String type;
    private String valueNameFirm;
    private String valueInsteadNameFirm;
    private LocalDateTime dtBeg;
    private LocalDateTime dtEnd;
    private Character isNowActive;
    private String userCreate;
    private LocalDateTime dataCreate;
}
