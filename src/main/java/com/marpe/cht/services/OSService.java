package com.marpe.cht.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.marpe.cht.entities.OS;
import com.marpe.cht.entities.OSColab;
import com.marpe.cht.entities.enums.DataState;
import com.marpe.cht.repositories.OSColabRepository;
import com.marpe.cht.repositories.OSRepository;
import com.marpe.cht.services.exceptions.DatabaseException;
import com.marpe.cht.services.exceptions.ResourceNotFoundException;

@Service
public class OSService {

	@Autowired
	private OSRepository repository;
	
	@Autowired
	private OSColabRepository oscolabRepository;
	
	
	public List<OS> findAll() {
		return repository.findAll();
	}
	
	public List<OS> findAllDescendingOrder() {
		List<OS> list = repository.findAll().stream()
				.sorted((f1, f2) -> Long.compare(f2.getId(), f1.getId()))
				.collect(Collectors.toList());
		return list;
	}
	
	public OS findById(Long id) {
		Optional<OS> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public OS insert(OS obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);	
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public void softDelete(Long id) {
		try {
			OS os = repository.getReferenceById(id);
			os.setState(DataState.DELETED);

			for(OSColab oscolab : os.getOscolab()) {
				oscolab.setState(DataState.DELETED);
				oscolabRepository.save(oscolab);
			}
			repository.save(os);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public OS update(Long id, OS obj) {
		try {
			OS entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(OS entity, OS obj) {
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