package com.marpe.cht.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marpe.cht.entities.Atividade;
import com.marpe.cht.entities.dtos.AtividadeRequest;
import com.marpe.cht.entities.dtos.AtividadeResponse;

@Mapper(componentModel = "spring")
public interface AtividadeMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "totalAReceber", ignore = true)
	@Mapping(target = "totalHorasDiurnas", ignore = true)
	@Mapping(target = "totalHorasNoturnas", ignore = true)
	@Mapping(target = "state", ignore = true)
	Atividade toAtividade(AtividadeRequest atividadeRequest);
    
	AtividadeResponse toAtividadeResponse(Atividade atividade);
}
