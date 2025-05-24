package com.marpe.cht.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marpe.cht.entities.Regional;
import com.marpe.cht.entities.enums.Datastate;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.exceptions.ResourceNotFoundException;
import com.marpe.cht.repositories.RegionalRepository;
import com.marpe.cht.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@Service
public class RegionalService {
	
	private static final Logger log = LoggerFactory.getLogger(RegionalService.class);
	
	private final RegionalRepository regionalRepository;
	
	public RegionalService(RegionalRepository regionalRepository) {
		this.regionalRepository = regionalRepository;
	}

	@Cacheable("regionals")
	public Page<Regional> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service to findAll Regionais");
		PageRequest pageRequest = PageRequest.of(
            paginationRequest.getPage(),
            paginationRequest.getSize(),
            Sort.by(Sort.Direction.fromString(paginationRequest.getSortDirection()), paginationRequest.getSortField()));
		Page<Regional> regionalPage = regionalRepository.findAll(pageRequest);
        return new PageImpl<Regional>(regionalPage.getContent(), regionalPage.getPageable(), regionalPage.getTotalElements());
	}
	
	public Regional findById(Long id) {
		log.info("Executing service to findById a Regional with param: id={}", id); 
		return regionalRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Regional not found with id: " + id));
	}
	
    @Transactional
	public Regional create(Regional request) {
		log.info("Executing service to create a Regional with param: regional={}", request);
		try {
			Regional regional = new Regional();
			regional.setNome(request.getNome());
			regional.setHorasPadrao(request.getHorasPadrao());
			regional.setValorHoraDiurna(request.getValorHoraDiurna());
			regional.setValorHoraNoturna(request.getValorHoraNoturna());
			regional.setValorTransporte(request.getValorTransporte());
			regional.setDescricao(request.getDescricao());
			regional.setState(Datastate.ACTIVE);
	        return regionalRepository.save(regional);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating Regional input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error registering Regional input data in database: " + e.getMessage());
		}
	}
    
    @Transactional
	public Regional update(Long id, Regional request) {
		log.info("Executing service to update a Regional with params: id={} and regional={}", id, request);
		try {
			Regional regional = findById(id);
			regional.setNome(request.getNome());
			regional.setHorasPadrao(request.getHorasPadrao());
			regional.setValorHoraDiurna(request.getValorHoraDiurna());
			regional.setValorHoraNoturna(request.getValorHoraNoturna());
			regional.setValorTransporte(request.getValorTransporte());
			regional.setDescricao(request.getDescricao());
			return regionalRepository.save(regional);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating Regional input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error updating Regional input data in database: " + e.getMessage());
		}
	}

	@Transactional
	public String delete(Long id) {
		log.info("Executing service to delete a Regional with param: id={}", id);
		try {
			regionalRepository.deleteById(id);
			return "Registro removido com sucesso.";
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Regional not found with id: " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
}
