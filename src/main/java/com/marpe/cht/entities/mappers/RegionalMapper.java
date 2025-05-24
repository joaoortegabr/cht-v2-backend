package com.marpe.cht.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marpe.cht.entities.Regional;
import com.marpe.cht.entities.dtos.RegionalRequest;
import com.marpe.cht.entities.dtos.RegionalResponse;

@Mapper(componentModel = "spring")
public interface RegionalMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "orders", ignore = true)
	@Mapping(target = "state", ignore = true)
	Regional toRegional(RegionalRequest regionalRequest);

	RegionalResponse toRegionalResponse(Regional regional);
}
