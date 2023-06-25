package ru.fssp.odpea.cruds.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Delegate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "TYPE")//(default = "OGRN|REGNON")
    private String type;
    @Column(name = "VALUE_NAME_FIRM")
    private String valueNameFirm;
    @Column(name = "VALUE_INSTEAD_NAME_FIRM")
    private String valueInsteadNameFirm;
    @Column(name = "DT_BEG")
    private ZonedDateTime dtBeg;
    @Column(name = "DT_END")
    private ZonedDateTime dtEnd;
    @Column(name = "IS_NOW_ACTIVE")
    private Character isNowActive;
    @Column(name = "USER_CREATE")
    private String userCreate;
    @Column(name = "DATA_CREATE")
    private ZonedDateTime dataCreate;
}