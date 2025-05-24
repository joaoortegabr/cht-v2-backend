package com.marpe.cht.services;

import java.time.format.DateTimeFormatter;

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

import com.marpe.cht.entities.Atividade;
import com.marpe.cht.entities.Order;
import com.marpe.cht.entities.enums.Datastate;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.exceptions.ResourceNotFoundException;
import com.marpe.cht.repositories.AtividadeRepository;
import com.marpe.cht.utils.CalculadoraHoras;
import com.marpe.cht.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@Service
public class AtividadeService {

	private static final Logger log = LoggerFactory.getLogger(AtividadeService.class);
	DateTimeFormatter dtfmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final AtividadeRepository atividadeRepository;
	private final CalculadoraHoras calculadora;

	public AtividadeService(AtividadeRepository atividadeRepository, CalculadoraHoras calculadora) {
		this.atividadeRepository = atividadeRepository;
		this.calculadora = calculadora;
	}
	
	@Cacheable("atividades")
	public Page<Atividade> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service to findAll Atividades");
		PageRequest pageRequest = PageRequest.of(
            paginationRequest.getPage(),
            paginationRequest.getSize(),
            Sort.by(Sort.Direction.DESC, paginationRequest.getSortField()));
		Page<Atividade> atividadePage = atividadeRepository.findAll(pageRequest);
        return new PageImpl<Atividade>(atividadePage.getContent(), atividadePage.getPageable(), atividadePage.getTotalElements());
	}
	
	public Atividade findById(Long id) {
		log.info("Executing service to findById an Atividade with param: id={}", id); 
		return atividadeRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Atividade not found with id: " + id));
	}
	
    @Transactional
	public Atividade create(Atividade request) {
		log.info("Executing service to create an Atividade with param: atividade={}", request);
		try {
			Atividade atividade = new Atividade();
			atividade.setOrder(request.getOrder());
			atividade.setColaborador(request.getColaborador());
			atividade.setHoraInicial(request.getHoraInicial());
			atividade.setHoraFinal(request.getHoraFinal());
			if(request.getHoraFinal() == null) {
				atividade.setTotalHorasDiurnas(0.0);
				atividade.setTotalHorasNoturnas(0.0);
				atividade.setTotalAReceber(0.0);
			} else {
				calculadora.calcularHorasAPagar(request, request.getOrder());
				atividade.setTotalHorasDiurnas(request.getTotalHorasDiurnas());
				atividade.setTotalHorasNoturnas(request.getTotalHorasNoturnas());
				calcularTotalAReceber(request, request.getOrder());
				atividade.setTotalAReceber(request.getTotalAReceber());
			}
			atividade.setIntervalo(request.getIntervalo());
			atividade.setTransportes(request.getTransportes());
			atividade.setAdicionalViagem(request.getAdicionalViagem());
			atividade.setPago(request.getPago());
			atividade.setState(Datastate.ACTIVE);
	        return atividadeRepository.save(atividade);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating Atividade input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error registering Atividade input data in database: " + e.getMessage());
		}
	}
    
    @Transactional
	public Atividade update(Long id, Atividade request) {
		log.info("Executing service to update an Atividade with params: id={} and atividade={}", id, request);
		try {
			Atividade atividade = new Atividade();
			atividade.setOrder(request.getOrder());
			atividade.setColaborador(request.getColaborador());
			atividade.setHoraInicial(request.getHoraInicial());
			atividade.setHoraFinal(request.getHoraFinal());
			if(request.getHoraFinal() == null) {
				atividade.setTotalHorasDiurnas(0.0);
				atividade.setTotalHorasNoturnas(0.0);
				atividade.setTotalAReceber(0.0);
			} else {
				calculadora.calcularHorasAPagar(request, request.getOrder());
				atividade.setTotalHorasDiurnas(request.getTotalHorasDiurnas());
				atividade.setTotalHorasNoturnas(request.getTotalHorasNoturnas());
				calcularTotalAReceber(request, request.getOrder());
				atividade.setTotalAReceber(request.getTotalAReceber());
			}
			atividade.setIntervalo(request.getIntervalo());
			atividade.setTransportes(request.getTransportes());
			atividade.setAdicionalViagem(request.getAdicionalViagem());
			atividade.setPago(request.getPago());
			return atividadeRepository.save(atividade);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating Cliente input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error updating Cliente input data in database: " + e.getMessage());
		}
	}

	@Transactional
	public String delete(Long id) {
		log.info("Executing service to delete an Atividade with param: id={}", id);
		try {
			atividadeRepository.deleteById(id);
			return "Registro removido com sucesso.";
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Atividade not found with id: " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public void calcularTotalAReceber(Atividade atividade, Order order) {
		
		Double valorHoraDiurna = order.getRegional().getValorHoraDiurna();
		Double valorHoraNoturna = order.getRegional().getValorHoraNoturna();
		Double valorTransporte = order.getRegional().getValorTransporte();
		Integer transportes = atividade.getTransportes();
		Double adicionalViagem = atividade.getAdicionalViagem();
		
		Double totalAReceber = 0.0;
		Double totalHorasDiurnas = atividade.getTotalHorasDiurnas();
		Double totalHorasNoturnas = atividade.getTotalHorasNoturnas();
		
		totalAReceber += (totalHorasDiurnas * valorHoraDiurna) + (totalHorasNoturnas * valorHoraNoturna);
		
		if(transportes > 0) {
			totalAReceber += (transportes * valorTransporte);
		}
		
		totalAReceber += adicionalViagem;
		
		atividade.setTotalAReceber(totalAReceber);
	}
	
	
	
	
//	public Atividade insert(Atividade obj) {
//		
//		Optional<Order> os = OrderRepository.findById(obj.getOs().getId());
//		try {
//			if(obj.getHoraFinal() != null) {
//				calcularHorasAPagar(obj, os.get());
//				calcularTotalAReceber(obj, os.get());
//			}
//			os.get().addOscolab(obj);
//			return repository.save(obj);
//		} catch (Exception e) {
//			throw new ResourceNotFoundException("Resource not found with id: " + obj);
//		}
//	}


}