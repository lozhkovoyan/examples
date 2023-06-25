package ru.fssp.odpea.cruds.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.fssp.odpea.cruds.dto.DelegateDtoRequest;
import ru.fssp.odpea.cruds.dto.DelegateDtoResponse;
import ru.fssp.odpea.cruds.model.Delegate;

@Mapper(componentModel = "spring")
public interface DelegateMapper {
    DelegateMapper INSTANCE = Mappers.getMapper(DelegateMapper.class);

    void mapFromDtoReq(@MappingTarget Delegate delegate, DelegateDtoRequest delegateDtoRequest);

    void mapToDtoResp(@MappingTarget DelegateDtoResponse delegateDtoResponse, Delegate delegate);

}
