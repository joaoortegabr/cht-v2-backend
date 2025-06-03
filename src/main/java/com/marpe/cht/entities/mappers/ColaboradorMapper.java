package com.marpe.cht.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marpe.cht.entities.Colaborador;
import com.marpe.cht.entities.dtos.ColaboradorRequest;
import com.marpe.cht.entities.dtos.ColaboradorResponse;

@Mapper(componentModel = "spring")
public interface ColaboradorMapper {

	@Mapping(target = "id", ignore = true)
//	@Mapping(source = "nome", target = "dadosPessoais.nome")
//	@Mapping(source = "rg", target = "dadosPessoais.rg")
//	@Mapping(source = "cpf", target = "dadosPessoais.cpf")
//	@Mapping(source = "telefone", target = "dadosPessoais.telefone")
//	@Mapping(source = "email", target = "dadosPessoais.email")
//	@Mapping(source = "cidade", target = "dadosPessoais.cidade")
	@Mapping(target = "state", ignore = true)
	Colaborador toColaborador(ColaboradorRequest colaboradorRequest);
    
	ColaboradorResponse toColaboradorResponse(Colaborador colaborador);
}
