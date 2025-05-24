package com.marpe.cht.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marpe.cht.entities.Coordenador;
import com.marpe.cht.entities.dtos.CoordenadorRequest;
import com.marpe.cht.entities.dtos.CoordenadorResponse;


@Mapper(componentModel = "spring")
public interface CoordenadorMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "orders", ignore = true)
	@Mapping(target = "state", ignore = true)
	Coordenador toCoordenador(CoordenadorRequest coordenadorRequest);
    
	CoordenadorResponse toCoordenadorResponse(Coordenador coordenador);
}
