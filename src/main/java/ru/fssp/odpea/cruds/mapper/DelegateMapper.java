package ru.fssp.odpea.cruds.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.fssp.odpea.cruds.dto.ModelNameDto;
import ru.fssp.odpea.cruds.model.Delegate;

@Mapper(componentModel = "spring")
public interface DelegateMapper {
    DelegateMapper INSTANCE = Mappers.getMapper(DelegateMapper.class);
    Delegate mapToDto(ModelNameDto modelNameDto);
    ModelNameDto mapFromDto(Delegate delegate);

}
