package com.marpe.cht.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marpe.cht.entities.dtos.CidadeResponse;
import com.marpe.cht.services.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	private final CidadeService cidadeService;
	
	public CidadeController(CidadeService cidadeService) {
		this.cidadeService = cidadeService;
	}

	@GetMapping
	public List<CidadeResponse> findAll() {
		return cidadeService.getAllCidades();
	}
    
}
