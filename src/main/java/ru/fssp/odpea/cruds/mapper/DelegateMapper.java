/*
package ru.fssp.odpea.cruds.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.fssp.odpea.cruds.dto.DelegateDto;

@Mapper(componentModel = "spring")
public interface DelegateMapper {
    DelegateMapper INSTANCE = Mappers.getMapper(DelegateMapper.class);
    DelegateDto mapToDto(ru.fssp.odpea.cruds.model.Delegate delegate);
    DelegateDto mapFromDto(ru.fssp.odpea.cruds.model.Delegate delegate);
*/
package ru.fssp.odpea.cruds.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.fssp.odpea.cruds.dto.DelegateDto;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.model.Delegate;

@Mapper(componentModel = "spring")
public interface DelegateMapper {
    DelegateMapper INSTANCE = Mappers.getMapper(DelegateMapper.class);

    DelegateDtoRequest mapToDto(Delegate delegate);

    DelegateDto mapFromDto(Delegate delegate);

    Delegate mapFromDto(DelegateDto delegateDto);

    Delegate mapFromDto(@MappingTarget Delegate delegate, DelegateDto delegateDto);

}
