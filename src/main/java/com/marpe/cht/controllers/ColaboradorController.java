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

import com.marpe.cht.entities.Colaborador;
import com.marpe.cht.entities.dtos.ColaboradorRequest;
import com.marpe.cht.entities.dtos.ColaboradorResponse;
import com.marpe.cht.entities.mappers.ColaboradorMapper;
import com.marpe.cht.services.ColaboradorService;
import com.marpe.cht.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/colaboradores")
public class ColaboradorController {

	private static final Logger log = LoggerFactory.getLogger(ColaboradorController.class);
	ColaboradorMapper mapper = Mappers.getMapper(ColaboradorMapper.class);
	
	private final ColaboradorService colaboradorService;
	
	public ColaboradorController(ColaboradorService colaboradorService) {
		this.colaboradorService = colaboradorService;
	}

	@GetMapping
	public ResponseEntity<Page<ColaboradorResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll Colaboradores");
		Page<Colaborador> colaboradorPage = colaboradorService.findAll(paginationRequest);
		Page<ColaboradorResponse> colaboradorResponsePage = colaboradorPage.map(mapper::toColaboradorResponse);
	    return ResponseEntity.ok(colaboradorResponsePage);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ColaboradorResponse> findById(@PathVariable Long id) {
		log.info("Receiving request to findById a Colaborador with param: id={}", id);
		ColaboradorResponse colaborador = mapper.toColaboradorResponse(colaboradorService.findById(id));
		return ResponseEntity.ok().body(colaborador);
	}
	
	@PostMapping
	public ResponseEntity<ColaboradorResponse> create(@RequestBody ColaboradorRequest request) {
		log.info("Receiving request to create a Colaborador with param: colaborador={}", request);
		Colaborador colaborador = mapper.toColaborador(request);
		Colaborador createdColaborador = colaboradorService.create(colaborador);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdColaborador.getId()).toUri();
		ColaboradorResponse colaboradorResponse = mapper.toColaboradorResponse(createdColaborador);
		return ResponseEntity.created(uri).body(colaboradorResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ColaboradorResponse> update(@PathVariable Long id, @RequestBody ColaboradorRequest request) {
		log.info("Receiving request to update a Colaborador with params: id={} and colaborador={}", id, request);
		Colaborador colaborador = mapper.toColaborador(request);
		Colaborador updatedColaborador = colaboradorService.update(id, colaborador);
		ColaboradorResponse colaboradorResponse = mapper.toColaboradorResponse(updatedColaborador);
		return ResponseEntity.ok().body(colaboradorResponse);
	}	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("Receiving request to delete a Colaborador with param: id={}", id);
		String msg = colaboradorService.delete(id);
		return ResponseEntity.ok(msg);
	}
	
}
