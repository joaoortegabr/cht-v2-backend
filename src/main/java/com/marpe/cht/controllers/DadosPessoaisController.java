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

import com.marpe.cht.entities.DadosPessoais;
import com.marpe.cht.entities.dtos.DadosPessoaisRequest;
import com.marpe.cht.entities.dtos.DadosPessoaisResponse;
import com.marpe.cht.entities.mappers.DadosPessoaisMapper;
import com.marpe.cht.services.DadosPessoaisService;

@RestController
@RequestMapping(value = "/dados-pessoais")
public class DadosPessoaisController {

	private static final Logger log = LoggerFactory.getLogger(DadosPessoaisController.class);
	DadosPessoaisMapper mapper = Mappers.getMapper(DadosPessoaisMapper.class);
	
	private final DadosPessoaisService dadosPessoaisService;

	public DadosPessoaisController(DadosPessoaisService dadosPessoaisService) {
		this.dadosPessoaisService = dadosPessoaisService;
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DadosPessoaisResponse> findById(@PathVariable Long id) {
		log.info("Receiving request to findById a DadosPessoais with param: id={}", id);
		DadosPessoaisResponse dadosPessoais = mapper.toDadosPessoaisResponse(dadosPessoaisService.findById(id));
		return ResponseEntity.ok().body(dadosPessoais);
	}
	
	@PostMapping
	public ResponseEntity<DadosPessoaisResponse> create(@RequestBody DadosPessoaisRequest request) {
		log.info("Receiving request to create a DadosPessoais with param: dadosPessoais={}", request);
		DadosPessoais dadosPessoais = mapper.toDadosPessoais(request);
		DadosPessoais createdDadosPessoais = dadosPessoaisService.create(dadosPessoais);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdDadosPessoais.getId()).toUri();
		DadosPessoaisResponse dadosPessoaisResponse = mapper.toDadosPessoaisResponse(createdDadosPessoais);
		return ResponseEntity.created(uri).body(dadosPessoaisResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<DadosPessoaisResponse> update(@PathVariable Long id, @RequestBody DadosPessoaisRequest request) {
		log.info("Receiving request to update a DadosPessoais with params: id={} and dadosPessoais={}", id, request);
		DadosPessoais dadosPessoais = mapper.toDadosPessoais(request);
		DadosPessoais updatedDadosPessoais = dadosPessoaisService.update(id, dadosPessoais);
		DadosPessoaisResponse dadosPessoaisResponse = mapper.toDadosPessoaisResponse(updatedDadosPessoais);
		return ResponseEntity.ok().body(dadosPessoaisResponse);
	}	
	
}
