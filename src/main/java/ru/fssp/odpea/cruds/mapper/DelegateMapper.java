/*
package ru.fssp.odpea.cruds.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.fssp.odpea.cruds.dto.DelegateDto;

@Mapper(componentModel = "spring")
public interface DelegateMapper {
    DelegateMapper INSTANCE = Mappers.getMapper(DelegateMapper.class);
    DelegateDto mapToDtoResp(ru.fssp.odpea.cruds.model.Delegate delegate);
    DelegateDto mapFromDto(ru.fssp.odpea.cruds.model.Delegate delegate);
*/
package ru.fssp.odpea.cruds.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.dto.DelegateDtoResponce;
import ru.fssp.odpea.cruds.model.Delegate;

@Mapper(componentModel = "spring")
public interface DelegateMapper {
    DelegateMapper INSTANCE = Mappers.getMapper(DelegateMapper.class);

//    DelegateDtoResponce mapToDtoResp(Delegate delegate);

//    DelegateDtoRequest mapFromDto(Delegate delegate);

    Delegate mapFromDto(DelegateDtoRequest delegateDtoRequest);

    void mapFromDtoReq(@MappingTarget Delegate delegate, DelegateDtoRequest delegateDtoRequest);
    void mapToDtoReq(@MappingTarget DelegateDtoRequest delegateDtoRequest, Delegate delegate);
    void mapToDtoResp(@MappingTarget DelegateDtoResponce delegateDtoResponce, Delegate delegate);

}
