package com.app.login.mapper;

import com.app.login.model.App;
import com.app.login.dto.AppDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AppMapper {
    void updateEntityFromDto(AppDTO appDTO, @MappingTarget App app1);

    AppDTO toDto(App entity);

    App toEntity(AppDTO appDTO);
}
