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

import com.marpe.cht.entities.Regional;
import com.marpe.cht.entities.dtos.RegionalRequest;
import com.marpe.cht.entities.dtos.RegionalResponse;
import com.marpe.cht.entities.mappers.RegionalMapper;
import com.marpe.cht.services.RegionalService;
import com.marpe.cht.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/regionais")
public class RegionalController {

	private static final Logger log = LoggerFactory.getLogger(RegionalController.class);
	RegionalMapper mapper = Mappers.getMapper(RegionalMapper.class);
	
	private final RegionalService regionalService;

	public RegionalController(RegionalService regionalService) {
		this.regionalService = regionalService;
	}
	
	@GetMapping
	public ResponseEntity<Page<RegionalResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll Regionais");
		Page<Regional> regionalPage = regionalService.findAll(paginationRequest);
		Page<RegionalResponse> regionalResponsePage = regionalPage.map(mapper::toRegionalResponse);
	    return ResponseEntity.ok(regionalResponsePage);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<RegionalResponse> findById(@PathVariable Long id) {
		log.info("Receiving request to findById a Regional with param: id={}", id);
		RegionalResponse regional = mapper.toRegionalResponse(regionalService.findById(id));
		return ResponseEntity.ok().body(regional);
	}
	
	@PostMapping
	public ResponseEntity<RegionalResponse> create(@RequestBody RegionalRequest request) {
		log.info("Receiving request to create a Regional with param: regional={}", request);
		Regional regional = mapper.toRegional(request);
		Regional createdRegional = regionalService.create(regional);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdRegional.getId()).toUri();
		RegionalResponse regionalResponse = mapper.toRegionalResponse(createdRegional);
		return ResponseEntity.created(uri).body(regionalResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<RegionalResponse> update(@PathVariable Long id, @RequestBody RegionalRequest request) {
		log.info("Receiving request to update a Regional with params: id={} and regional={}", id, request);
		Regional regional = mapper.toRegional(request);
		Regional updatedRegional = regionalService.update(id, regional);
		RegionalResponse regionalResponse = mapper.toRegionalResponse(updatedRegional);
		return ResponseEntity.ok().body(regionalResponse);
	}	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("Receiving request to delete a Regional with param: id={}", id);
		String msg = regionalService.delete(id);
		return ResponseEntity.ok(msg);
	}
	
}
