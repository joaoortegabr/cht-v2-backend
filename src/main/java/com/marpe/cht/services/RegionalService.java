package com.marpe.cht.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.marpe.cht.entities.Regional;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.repositories.RegionalRepository;
import com.marpe.cht.exceptions.ResourceNotFoundException;

@Service
public class RegionalService {

	@Autowired
	private RegionalRepository repository;
	
	public List<Regional> findAll() {
		return repository.findAll();
	}
	
	public Regional findById(Long id) {
		Optional<Regional> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
	}
	
	public Regional insert(Regional obj) {
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
	
	public Regional update(Long id, Regional obj) {
		try {
			Regional entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Resource not found with id: " + id);
		}
	}

	private void updateData(Regional entity, Regional obj) {
		entity.setNome(obj.getNome());
		entity.setHorasPadrao(obj.getHorasPadrao());
		entity.setValorHoraDiurna(obj.getValorHoraDiurna());
		entity.setValorHoraNoturno(obj.getValorHoraNoturna());
		entity.setValorTransporte(obj.getValorTransporte());
		entity.setDescricao(obj.getDescricao());
	}
	
}
