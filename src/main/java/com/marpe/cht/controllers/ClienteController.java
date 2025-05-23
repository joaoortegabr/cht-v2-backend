package com.marpe.cht.controllers;

import java.net.URI;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marpe.cht.entities.Cliente;
import com.marpe.cht.entities.dtos.ClienteRequest;
import com.marpe.cht.entities.dtos.ClienteResponse;
import com.marpe.cht.entities.mappers.ClienteMapper;
import com.marpe.cht.services.ClienteService;
import com.marpe.cht.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	private static final Logger log = LoggerFactory.getLogger(ClienteController.class);
	ClienteMapper mapper = Mappers.getMapper(ClienteMapper.class);
	
	private final ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@GetMapping
	public ResponseEntity<Page<ClienteResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll Clientes");
		Page<Cliente> clientePage = clienteService.findAll(paginationRequest);
		Page<ClienteResponse> clienteResponsePage = clientePage.map(mapper::toClienteResponse);
	    return ResponseEntity.ok(clienteResponsePage);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteResponse>findById(@PathVariable Long id) {
		log.info("Receiving request to findById a Cliente with param: id={}", id);
		ClienteResponse cliente = mapper.toClienteResponse(clienteService.findById(id));
		return ResponseEntity.ok().body(cliente);
	}
	
	@PostMapping
	public ResponseEntity<ClienteResponse> create(@RequestBody ClienteRequest request) {
		log.info("Receiving request to create a Cliente with param: cliente={}", request);
		Cliente cliente = mapper.toCliente(request);
		Cliente createdCliente = clienteService.create(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdCliente.getId()).toUri();
		ClienteResponse clienteResponse = mapper.toClienteResponse(createdCliente);
		return ResponseEntity.created(uri).body(clienteResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteResponse> update(@PathVariable Long id, @RequestBody ClienteRequest request) {
		log.info("Receiving request to update a Cliente with params: id={} and cliente={}", id, request);
		Cliente cliente = mapper.toCliente(request);
		Cliente updatedCliente = clienteService.update(id, cliente);
		ClienteResponse clienteResponse = mapper.toClienteResponse(updatedCliente);
		return ResponseEntity.ok().body(clienteResponse);
	}	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("Receiving request to delete a Cliente with param: id={}", id);
		String msg = clienteService.delete(id);
		return ResponseEntity.ok(msg);
	}	
	
}
