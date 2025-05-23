package com.marpe.cht.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.marpe.cht.entities.Colaborador;
import com.marpe.cht.entities.Atividade;
import com.marpe.cht.entities.User;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.repositories.ColaboradorRepository;
import com.marpe.cht.exceptions.ResourceNotFoundException;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository repository;
	
	@Autowired
	private OSColabService oscolabService;
	
	@Autowired
	private UserService userService;
	
	public List<Colaborador> findAll() {
		return repository.findAll();
	}
	
	public List<Colaborador> findAllDescendingOrder() {
		List<Colaborador> list = repository.findAll().stream()
				.sorted((f1, f2) -> Long.compare(f2.getId(), f1.getId()))
				.collect(Collectors.toList());
		return list;
	}
	
	public Colaborador findById(Long id) {
		Optional<Colaborador> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
	}
	
	public Colaborador insert(Colaborador obj) {
		return repository.save(obj);
	}
	
//	public void delete(Long id) {
//		try {
//			Colaborador colaborador = findById(id);
//			substituiColaboradorNulo(colaborador);
//			repository.deleteById(id);
//		} catch(EmptyResultDataAccessException e) {
//			throw new ResourceNotFoundException("Resource not found with id: " + id);
//		} catch(DataIntegrityViolationException e) {
//			throw new DatabaseException(e.getMessage());
//		}
//	}
//	
	public Colaborador update(Long id, Colaborador obj) {
		try {
			Colaborador entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Resource not found with id: " + id);
		}
	}

	private void updateData(Colaborador entity, Colaborador obj) {
//		entity.setUser(obj.getUser());
//		entity.setBanco(obj.getBanco());
//		entity.setAgencia(obj.getAgencia());
//		entity.setConta(obj.getConta());
//		entity.setOperacao(obj.getOperacao());
//		entity.setTitular(obj.getTitular());
//		entity.setCidade(obj.getCidade());
	}
	
//	private void substituiColaboradorNulo(Colaborador colaborador) {
//		List<OSColab> colabs = oscolabService.findAll();
//		colabs.stream()
//			.forEach(x -> {
//				if(x.getColaborador().equals(colaborador)) {
//					x.setColaborador(criaColaboradorNulo());
//
//				}
//			}
//		);
//		
//	}
	
//	public Colaborador criaColaboradorNulo() {
//		User user = userService.criaUserNulo();
//		Colaborador colaborador = new Colaborador();
//		colaborador.setUser(user);
//		colaborador.setBanco("nulo");
//		colaborador.setAgencia("nulo");
//		colaborador.setConta("nulo");
//		colaborador.setOperacao("nulo");
//		colaborador.setTitular("nulo");
//		colaborador.setCidade(new Cidade());
//		return colaborador;
//	}
	

	
	
}
