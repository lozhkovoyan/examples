package ru.fssp.odpea.cruds.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.fssp.odpea.cruds.dto.ModelNameDto;
import ru.fssp.odpea.cruds.model.ModelName;

@Mapper(componentModel = "spring")
public interface ModelNameMapper {
    ModelNameMapper INSTANCE = Mappers.getMapper(ModelNameMapper.class);
    ModelName mapToDto(ModelNameDto modelNameDto);
    ModelNameDto mapFromDto(ModelName modelName);

}
