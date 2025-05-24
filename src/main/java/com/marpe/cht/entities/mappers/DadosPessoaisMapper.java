package com.marpe.cht.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marpe.cht.entities.DadosPessoais;
import com.marpe.cht.entities.dtos.DadosPessoaisRequest;
import com.marpe.cht.entities.dtos.DadosPessoaisResponse;

@Mapper(componentModel = "spring")
public interface DadosPessoaisMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "state", ignore = true)
	DadosPessoais toDadosPessoais(DadosPessoaisRequest dadosPessoaisRequest);
    
	DadosPessoaisResponse toDadosPessoaisResponse(DadosPessoais dadosPessoais);
}
