package ru.fssp.odpea.cruds.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.dto.DelegateDtoResponse;
import ru.fssp.odpea.object.Delegate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DelegateMapper {
    DelegateMapper INSTANCE = Mappers.getMapper(DelegateMapper.class);

//    @Mapping(,,) добавить нужные поля через маппер
    void mapFromDtoReq(@MappingTarget Delegate delegate, DelegateDtoRequest delegateDtoRequest);

    void mapToDtoResp(@MappingTarget DelegateDtoResponse delegateDtoResponse, Delegate delegate);

}
