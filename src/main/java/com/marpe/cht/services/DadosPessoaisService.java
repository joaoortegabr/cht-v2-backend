package com.marpe.cht.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marpe.cht.entities.DadosPessoais;
import com.marpe.cht.entities.User;
import com.marpe.cht.entities.enums.Datastate;
import com.marpe.cht.exceptions.ResourceNotFoundException;
import com.marpe.cht.repositories.DadosPessoaisRepository;
import com.marpe.cht.repositories.UserRepository;

import jakarta.validation.ConstraintViolationException;

@Service
public class DadosPessoaisService {

	private static final Logger log = LoggerFactory.getLogger(DadosPessoaisService.class);
	
	private final DadosPessoaisRepository dadosPessoaisRepository;
	private final UserRepository userRepository;
	
	public DadosPessoaisService(DadosPessoaisRepository dadosPessoaisRepository, UserRepository userRepository) {
		this.dadosPessoaisRepository = dadosPessoaisRepository;
		this.userRepository = userRepository;
	}

	public DadosPessoais findById(Long id) {
		log.info("Executing service to findById a DadosPessoais with param: id={}", id); 
		return dadosPessoaisRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("DadosPessoais not found with id: " + id));
	}
	
    @Transactional
	public DadosPessoais create(DadosPessoais request) {
		log.info("Executing service to create a DadosPessoais with param: dadosPessoais={}", request);
		try {
			User user = userRepository.getReferenceById(request.getUser().getId());
			DadosPessoais dadosPessoais = new DadosPessoais();
			dadosPessoais.setUser(user);
			dadosPessoais.setRg(request.getRg());
			dadosPessoais.setCpf(request.getCpf());
			dadosPessoais.setTelefone(request.getTelefone());
			dadosPessoais.setEmail(request.getEmail());
			dadosPessoais.setState(Datastate.ACTIVE);
	        return dadosPessoaisRepository.save(dadosPessoais);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating DadosPessoais input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			log.error("Error registering DadosPessoais input data in database: {}", e.getMessage());
			throw new DataIntegrityViolationException("Error registering DadosPessoais input data in database.");
		}
	}
    
    @Transactional
	public DadosPessoais update(Long id, DadosPessoais request) {
		log.info("Executing service to update a DadosPessoais with params: id={} and dadosPessoais={}", id, request);
		try {
			DadosPessoais dadosPessoais = findById(id);
			dadosPessoais.setRg(request.getRg());
			dadosPessoais.setCpf(request.getCpf());
			dadosPessoais.setTelefone(request.getTelefone());
			dadosPessoais.setEmail(request.getEmail());
			return dadosPessoaisRepository.save(dadosPessoais);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating DadosPessoais input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			log.error("Error updating DadosPessoais input data in database: {}", e.getMessage());
			throw new DataIntegrityViolationException("Error updating DadosPessoais input data in database.");
		}
	}
    
}
