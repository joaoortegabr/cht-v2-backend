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

import com.marpe.cht.entities.Colaborador;
import com.marpe.cht.entities.enums.Datastate;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.exceptions.ResourceNotFoundException;
import com.marpe.cht.repositories.ColaboradorRepository;
import com.marpe.cht.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@Service
public class ColaboradorService {

	private static final Logger log = LoggerFactory.getLogger(ColaboradorService.class);

	private final ColaboradorRepository colaboradorRepository;

	public ColaboradorService(ColaboradorRepository colaboradorRepository) {
		this.colaboradorRepository = colaboradorRepository;
	}
	
	@Cacheable("colaboradores")
	public Page<Colaborador> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service to findAll Colaboradores");
		PageRequest pageRequest = PageRequest.of(
            paginationRequest.getPage(),
            paginationRequest.getSize(),
            Sort.by(Sort.Direction.fromString(paginationRequest.getSortDirection()), paginationRequest.getSortField()));
		Page<Colaborador> colaboradorPage = colaboradorRepository.findAll(pageRequest);
        return new PageImpl<Colaborador>(colaboradorPage.getContent(), colaboradorPage.getPageable(), colaboradorPage.getTotalElements());
	}
	
	public Colaborador findById(Long id) {
		log.info("Executing service to findById a Colaborador with param: id={}", id); 
		return colaboradorRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Colaborador not found with id: " + id));
	}
	
    @Transactional
	public Colaborador create(Colaborador request) {
		log.info("Executing service to create a Colaborador with param: colaborador={}", request);
		try {
			Colaborador colaborador = new Colaborador();
			colaborador.setUser(request.getUser());
			colaborador.setDadosPessoais(request.getDadosPessoais());
			colaborador.setDadosBancarios(request.getDadosBancarios());
			colaborador.setCidade(request.getCidade());
			colaborador.setState(Datastate.ACTIVE);
	        return colaboradorRepository.save(colaborador);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating Colaborador input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error registering Colaborador input data in database: " + e.getMessage());
		}
	}
    
    @Transactional
	public Colaborador update(Long id, Colaborador request) {
		log.info("Executing service to update a Colaborador with params: id={} and colaborador={}", id, request);
		try {
			Colaborador colaborador = findById(id);
			colaborador.setUser(request.getUser());
			colaborador.setDadosPessoais(request.getDadosPessoais());
			colaborador.setDadosBancarios(request.getDadosBancarios());
			colaborador.setCidade(request.getCidade());
			return colaboradorRepository.save(colaborador);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating Colaborador input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error updating Colaborador input data in database: " + e.getMessage());
		}
	}

	@Transactional
	public String delete(Long id) {
		log.info("Executing service to delete a Colaborador with param: id={}", id);
		try {
			colaboradorRepository.deleteById(id);
			return "Registro removido com sucesso.";
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Colaborador not found with id: " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
}
