package com.marpe.cht.controllers;

import java.net.URI;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marpe.cht.entities.DadosBancarios;
import com.marpe.cht.entities.dtos.DadosBancariosRequest;
import com.marpe.cht.entities.dtos.DadosBancariosResponse;
import com.marpe.cht.entities.mappers.DadosBancariosMapper;
import com.marpe.cht.services.DadosBancariosService;

@RestController
@RequestMapping(value = "/dados-bancarios")
public class DadosBancariosController {

	private static final Logger log = LoggerFactory.getLogger(DadosBancariosController.class);
	DadosBancariosMapper mapper = Mappers.getMapper(DadosBancariosMapper.class);
	
	private final DadosBancariosService dadosBancariosService;

	public DadosBancariosController(DadosBancariosService dadosBancariosService) {
		this.dadosBancariosService = dadosBancariosService;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DadosBancariosResponse> findById(@PathVariable Long id) {
		log.info("Receiving request to findById a DadosBancarios with param: id={}", id);
		DadosBancariosResponse dadosBancarios = mapper.toDadosBancariosResponse(dadosBancariosService.findById(id));
		return ResponseEntity.ok().body(dadosBancarios);
	}
	
	@PostMapping
	public ResponseEntity<DadosBancariosResponse> create(@RequestBody DadosBancariosRequest request) {
		log.info("Receiving request to create a DadosBancarios with param: dadosBancarios={}", request);
		DadosBancarios dadosBancarios = mapper.toDadosBancarios(request);
		DadosBancarios createdDadosBancarios = dadosBancariosService.create(dadosBancarios);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdDadosBancarios.getId()).toUri();
		DadosBancariosResponse dadosBancariosResponse = mapper.toDadosBancariosResponse(createdDadosBancarios);
		return ResponseEntity.created(uri).body(dadosBancariosResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<DadosBancariosResponse> update(@PathVariable Long id, @RequestBody DadosBancariosRequest request) {
		log.info("Receiving request to update a DadosBancarios with params: id={} and dadosBancarios={}", id, request);
		DadosBancarios dadosBancarios = mapper.toDadosBancarios(request);
		DadosBancarios updatedDadosBancarios = dadosBancariosService.update(id, dadosBancarios);
		DadosBancariosResponse dadosBancariosResponse = mapper.toDadosBancariosResponse(updatedDadosBancarios);
		return ResponseEntity.ok().body(dadosBancariosResponse);
	}	
	
}
