package ru.fssp.odpea.cruds.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.fssp.odpea.cruds.dto.DelegateDto;
import ru.fssp.odpea.cruds.model.Delegate;

@Mapper(componentModel = "spring")
public interface DelegateMapper {
    DelegateMapper INSTANCE = Mappers.getMapper(DelegateMapper.class);
    DelegateDto mapToDto(Delegate delegate);
    DelegateDto mapFromDto(Delegate delegate);

}
