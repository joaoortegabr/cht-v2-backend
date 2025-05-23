package com.marpe.cht.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.marpe.cht.entities.Order;
import com.marpe.cht.entities.Atividade;
import com.marpe.cht.entities.enums.Datastate;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.repositories.OSColabRepository;
import com.marpe.cht.repositories.OSRepository;
import com.marpe.cht.exceptions.ResourceNotFoundException;

@Service
public class OSService {

	@Autowired
	private OSRepository repository;
	
	@Autowired
	private OSColabRepository oscolabRepository;
	
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public List<Order> findAllDescendingOrder() {
		List<Order> list = repository.findAll().stream()
				.sorted((f1, f2) -> Long.compare(f2.getId(), f1.getId()))
				.collect(Collectors.toList());
		return list;
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
	}
	
	public Order insert(Order obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);	
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Resource not found with id: " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public void softDelete(Long id) {
		try {
			Order os = repository.getReferenceById(id);
			os.setState(Datastate.DELETED);

			for(Atividade oscolab : os.getOscolab()) {
				oscolab.setState(Datastate.DELETED);
				oscolabRepository.save(oscolab);
			}
			repository.save(os);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Resource not found with id: " + id);
		}
	}
	
	public Order update(Long id, Order obj) {
		try {
			Order entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Resource not found with id: " + id);
		}
	}

	private void updateData(Order entity, Order obj) {
		entity.setDataInicio(obj.getDataInicio());
		entity.setHoraInicio(obj.getHoraInicio());
		entity.setObservacao(obj.getObservacao());
		entity.setTodosPagos(obj.getTodosPagos());
		entity.setConcluida(obj.getConcluida());
		entity.setCliente(obj.getCliente());
		entity.setRegional(obj.getRegional());
		entity.setCoordenador(obj.getCoordenador());
	}
	
}