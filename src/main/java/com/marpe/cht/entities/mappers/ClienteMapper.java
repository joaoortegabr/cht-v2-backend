package com.marpe.cht.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marpe.cht.entities.Cliente;
import com.marpe.cht.entities.dtos.ClienteRequest;
import com.marpe.cht.entities.dtos.ClienteResponse;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "orders", ignore = true)
	@Mapping(target = "state", ignore = true)
	Cliente toCliente(ClienteRequest clienteRequest);
    
	ClienteResponse toClienteResponse(Cliente cliente);
}
