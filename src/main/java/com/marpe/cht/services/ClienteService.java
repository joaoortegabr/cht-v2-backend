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

import com.marpe.cht.entities.Cliente;
import com.marpe.cht.entities.enums.Datastate;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.exceptions.ResourceNotFoundException;
import com.marpe.cht.repositories.ClienteRepository;
import com.marpe.cht.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@Service
public class ClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	private final ClienteRepository clienteRepository;
	
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Cacheable("clientes")
	public Page<Cliente> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service to findAll Clientes");
		PageRequest pageRequest = PageRequest.of(
            paginationRequest.getPage(),
            paginationRequest.getSize(),
            Sort.by(Sort.Direction.fromString(paginationRequest.getSortDirection()), paginationRequest.getSortField()));
		Page<Cliente> clientePage = clienteRepository.findAll(pageRequest);
        return new PageImpl<Cliente>(clientePage.getContent(), clientePage.getPageable(), clientePage.getTotalElements());
	}
	
	public Cliente findById(Long id) {
		log.info("Executing service to findById a Cliente with param: id={}", id); 
		return clienteRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Cliente not found with id: " + id));
	}
	
    @Transactional
	public Cliente create(Cliente request) {
		log.info("Executing service to create a Cliente with param: cliente={}", request);
		try {
			Cliente cliente = new Cliente();
			cliente.setNome(request.getNome());
			cliente.setCidade(request.getCidade());
			cliente.setState(Datastate.ACTIVE);
	        return clienteRepository.save(cliente);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			log.error("Error registering input data in database: {}", e.getMessage());
			throw new DataIntegrityViolationException("Error registering input data in database.");
		}
	}
    
    @Transactional
	public Cliente update(Long id, Cliente request) {
		log.info("Executing service to update a Cliente with params: id={} and cliente={}", id, request);
		try {
			Cliente cliente = findById(id);
			cliente.setNome(request.getNome());
			cliente.setCidade(request.getCidade());
			return clienteRepository.save(cliente);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			log.error("Error updating input data in database: {}", e.getMessage());
			throw new DataIntegrityViolationException("Error updating input data in database.");
		}
	}

	@Transactional
	public String delete(Long id) {
		log.info("Executing service to delete a Cliente with param: id={}", id);
		try {
			Cliente cliente = findById(id);
			cliente.setState(Datastate.DELETED);
			//clienteRepository.deleteById(id);
			return "Registro removido com sucesso.";
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Cliente not found with id: " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
}
