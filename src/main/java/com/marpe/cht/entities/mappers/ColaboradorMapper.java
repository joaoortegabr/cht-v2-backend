package com.marpe.cht.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marpe.cht.entities.Colaborador;
import com.marpe.cht.entities.dtos.ColaboradorRequest;
import com.marpe.cht.entities.dtos.ColaboradorResponse;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper {

	@Mapping(target = "atividades", ignore = true)
	@Mapping(target = "state", ignore = true)
	Colaborador toColaborador(ColaboradorRequest colaboradorRequest);
    
	ColaboradorResponse toColaboradorResponse(Colaborador colaborador);
}
