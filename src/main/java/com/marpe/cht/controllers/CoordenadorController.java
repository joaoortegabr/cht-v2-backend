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

import com.marpe.cht.entities.Coordenador;
import com.marpe.cht.entities.dtos.CoordenadorRequest;
import com.marpe.cht.entities.dtos.CoordenadorResponse;
import com.marpe.cht.entities.mappers.CoordenadorMapper;
import com.marpe.cht.services.CoordenadorService;
import com.marpe.cht.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/coordenadores")
public class CoordenadorController {

	private static final Logger log = LoggerFactory.getLogger(CoordenadorController.class);
	CoordenadorMapper mapper = Mappers.getMapper(CoordenadorMapper.class);
	
	private final CoordenadorService coordenadorService;
	
	public CoordenadorController(CoordenadorService coordenadorService) {
		this.coordenadorService = coordenadorService;
	}

	@GetMapping
	public ResponseEntity<Page<CoordenadorResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll Coordenadores");
		Page<Coordenador> coordenadorPage = coordenadorService.findAll(paginationRequest);
		Page<CoordenadorResponse> coordenadorResponsePage = coordenadorPage.map(mapper::toCoordenadorResponse);
	    return ResponseEntity.ok(coordenadorResponsePage);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CoordenadorResponse> findById(@PathVariable Long id) {
		log.info("Receiving request to findById a Coordenador with param: id={}", id);
		CoordenadorResponse coordenador = mapper.toCoordenadorResponse(coordenadorService.findById(id));
		return ResponseEntity.ok().body(coordenador);
	}
	
	@PostMapping
	public ResponseEntity<CoordenadorResponse> create(@RequestBody CoordenadorRequest request) {
		log.info("Receiving request to create a Coordenador with param: coordenador={}", request);
		Coordenador coordenador = mapper.toCoordenador(request);
		Coordenador createdCoordenador = coordenadorService.create(coordenador);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdCoordenador.getId()).toUri();
		CoordenadorResponse coordenadorResponse = mapper.toCoordenadorResponse(createdCoordenador);
		return ResponseEntity.created(uri).body(coordenadorResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<CoordenadorResponse> update(@PathVariable Long id, @RequestBody CoordenadorRequest request) {
		log.info("Receiving request to update a Coordenador with params: id={} and coordenador={}", id, request);
		Coordenador coordenador = mapper.toCoordenador(request);
		Coordenador updatedCoordenador = coordenadorService.update(id, coordenador);
		CoordenadorResponse coordenadorResponse = mapper.toCoordenadorResponse(updatedCoordenador);
		return ResponseEntity.ok().body(coordenadorResponse);
	}	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("Receiving request to delete a Coordenador with param: id={}", id);
		String msg = coordenadorService.delete(id);
		return ResponseEntity.ok(msg);
	}
	
}
