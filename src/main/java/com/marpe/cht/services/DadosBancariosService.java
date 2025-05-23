package com.marpe.cht.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.marpe.cht.entities.DadosBancarios;
import com.marpe.cht.entities.User;
import com.marpe.cht.entities.enums.Datastate;
import com.marpe.cht.exceptions.ResourceNotFoundException;
import com.marpe.cht.repositories.DadosBancariosRepository;
import com.marpe.cht.repositories.UserRepository;

import jakarta.validation.ConstraintViolationException;

@Service
public class DadosBancariosService {

	private static final Logger log = LoggerFactory.getLogger(DadosBancariosService.class);
	
	private final DadosBancariosRepository dadosBancariosRepository;
	private final UserRepository userRepository;
	
	public DadosBancariosService(DadosBancariosRepository dadosBancariosRepository, UserRepository userRepository) {
		this.dadosBancariosRepository = dadosBancariosRepository;
		this.userRepository = userRepository;
	}

	public DadosBancarios findById(Long id) {
		log.info("Executing service to findById a DadosBancarios with param: id={}", id); 
		return dadosBancariosRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("DadosBancarios not found with id: " + id));
	}
	
    @Transactional
	public DadosBancarios create(DadosBancarios request) {
		log.info("Executing service to create a DadosBancarios with param: dadosBancarios={}", request);
		try {
			User user = userRepository.getReferenceById(request.getUser().getId());
			DadosBancarios dadosBancarios = new DadosBancarios();
			dadosBancarios.setUser(user);
			dadosBancarios.setBanco(request.getBanco());
			dadosBancarios.setAgencia(request.getAgencia());
			dadosBancarios.setConta(request.getConta());
			dadosBancarios.setOperacao(request.getOperacao());
			dadosBancarios.setTitular(request.getTitular());
			dadosBancarios.setState(Datastate.ACTIVE);
	        return dadosBancariosRepository.save(dadosBancarios);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating DadosBancarios input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			log.error("Error registering DadosBancarios input data in database: {}", e.getMessage());
			throw new DataIntegrityViolationException("Error registering DadosBancarios input data in database.");
		}
	}
    
    @Transactional
	public DadosBancarios update(Long id, DadosBancarios request) {
		log.info("Executing service to update a DadosBancarios with params: id={} and dadosBancarios={}", id, request);
		try {
			DadosBancarios dadosBancarios = findById(id);
			dadosBancarios.setBanco(request.getBanco());
			dadosBancarios.setAgencia(request.getAgencia());
			dadosBancarios.setConta(request.getConta());
			dadosBancarios.setOperacao(request.getOperacao());
			dadosBancarios.setTitular(request.getTitular());
			return dadosBancariosRepository.save(dadosBancarios);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating DadosBancarios input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			log.error("Error updating DadosBancarios input data in database: {}", e.getMessage());
			throw new DataIntegrityViolationException("Error updating DadosBancarios input data in database.");
		}
	}
    
}
