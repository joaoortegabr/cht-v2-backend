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

import com.marpe.cht.entities.Atividade;
import com.marpe.cht.entities.dtos.AtividadeRequest;
import com.marpe.cht.entities.dtos.AtividadeResponse;
import com.marpe.cht.entities.mappers.AtividadeMapper;
import com.marpe.cht.services.AtividadeService;
import com.marpe.cht.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/atividades")
public class AtividadeController {

	private static final Logger log = LoggerFactory.getLogger(AtividadeController.class);
	AtividadeMapper mapper = Mappers.getMapper(AtividadeMapper.class);

	private AtividadeService atividadeService;
	
	public AtividadeController(AtividadeService atividadeService) {
		this.atividadeService = atividadeService;
	}

	@GetMapping
	public ResponseEntity<Page<AtividadeResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll Atividades");
		Page<Atividade> atividadePage = atividadeService.findAll(paginationRequest);
		Page<AtividadeResponse> atividadeResponsePage = atividadePage.map(mapper::toAtividadeResponse);
	    return ResponseEntity.ok(atividadeResponsePage);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<AtividadeResponse> findById(@PathVariable Long id) {
		log.info("Receiving request to findById an Atividade with param: id={}", id);
		AtividadeResponse atividade = mapper.toAtividadeResponse(atividadeService.findById(id));
		return ResponseEntity.ok().body(atividade);
	}
	
	@PostMapping
	public ResponseEntity<AtividadeResponse> create(@RequestBody AtividadeRequest request) {
		log.info("Receiving request to create an Atividade with param: atividade={}", request);
		Atividade atividade = mapper.toAtividade(request);
		Atividade createdAtividade = atividadeService.create(atividade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdAtividade.getId()).toUri();
		AtividadeResponse atividadeResponse = mapper.toAtividadeResponse(createdAtividade);
		return ResponseEntity.created(uri).body(atividadeResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<AtividadeResponse> update(@PathVariable Long id, @RequestBody AtividadeRequest request) {
		log.info("Receiving request to update an Atividade with params: id={} and atividade={}", id, request);
		Atividade atividade = mapper.toAtividade(request);
		Atividade updatedAtividade = atividadeService.update(id, atividade);
		AtividadeResponse atividadeResponse = mapper.toAtividadeResponse(updatedAtividade);
		return ResponseEntity.ok().body(atividadeResponse);
	}	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("Receiving request to delete an Atividade with param: id={}", id);
		String msg = atividadeService.delete(id);
		return ResponseEntity.ok(msg);
	}	
	
}
