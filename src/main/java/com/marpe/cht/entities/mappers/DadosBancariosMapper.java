package com.marpe.cht.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marpe.cht.entities.DadosBancarios;
import com.marpe.cht.entities.dtos.DadosBancariosRequest;
import com.marpe.cht.entities.dtos.DadosBancariosResponse;

@Mapper(componentModel = "spring")
public interface DadosBancariosMapper {

	@Mapping(target = "state", ignore = true)
	DadosBancarios toDadosBancarios(DadosBancariosRequest dadosBancariosRequest);
    
	DadosBancariosResponse toDadosBancariosResponse(DadosBancarios dadosBancarios);
}
