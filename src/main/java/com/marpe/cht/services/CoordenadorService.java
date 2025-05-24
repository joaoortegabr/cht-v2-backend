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

import com.marpe.cht.entities.Coordenador;
import com.marpe.cht.entities.enums.Datastate;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.exceptions.ResourceNotFoundException;
import com.marpe.cht.repositories.CoordenadorRepository;
import com.marpe.cht.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@Service
public class CoordenadorService {

	private static final Logger log = LoggerFactory.getLogger(CoordenadorService.class);

	private final CoordenadorRepository coordenadorRepository;

	public CoordenadorService(CoordenadorRepository coordenadorRepository) {
		this.coordenadorRepository = coordenadorRepository;
	}
	
	@Cacheable("coordenadores")
	public Page<Coordenador> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service to findAll Coordenadores");
		PageRequest pageRequest = PageRequest.of(
            paginationRequest.getPage(),
            paginationRequest.getSize(),
            Sort.by(Sort.Direction.fromString(paginationRequest.getSortDirection()), paginationRequest.getSortField()));
		Page<Coordenador> coordenadorPage = coordenadorRepository.findAll(pageRequest);
        return new PageImpl<Coordenador>(coordenadorPage.getContent(), coordenadorPage.getPageable(), coordenadorPage.getTotalElements());
	}
	
	public Coordenador findById(Long id) {
		log.info("Executing service to findById a Coordenador with param: id={}", id); 
		return coordenadorRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Coordenador not found with id: " + id));
	}
	
    @Transactional
	public Coordenador create(Coordenador request) {
		log.info("Executing service to create a Coordenador with param: coordenador={}", request);
		try {
			Coordenador coordenador = new Coordenador();
			coordenador.setUser(request.getUser());
			coordenador.setDadosPessoais(request.getDadosPessoais());
			coordenador.setDadosBancarios(request.getDadosBancarios());
			coordenador.setState(Datastate.ACTIVE);
	        return coordenadorRepository.save(coordenador);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating Coordenador input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error registering Coordenador input data in database: " + e.getMessage());
		}
	}
    
    @Transactional
	public Coordenador update(Long id, Coordenador request) {
		log.info("Executing service to update a Coordenador with params: id={} and coordenador={}", id, request);
		try {
			Coordenador coordenador = findById(id);
			coordenador.setUser(request.getUser());
			coordenador.setDadosPessoais(request.getDadosPessoais());
			coordenador.setDadosBancarios(request.getDadosBancarios());
			return coordenadorRepository.save(coordenador);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating Coordenador input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error updating Coordenador input data in database: " + e.getMessage());
		}
	}

	@Transactional
	public String delete(Long id) {
		log.info("Executing service to delete a Coordenador with param: id={}", id);
		try {
			coordenadorRepository.deleteById(id);
			return "Registro removido com sucesso.";
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Coordenador not found with id: " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
}
