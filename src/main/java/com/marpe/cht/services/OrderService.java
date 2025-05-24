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

import com.marpe.cht.entities.Order;
import com.marpe.cht.entities.enums.Datastate;
import com.marpe.cht.exceptions.DatabaseException;
import com.marpe.cht.exceptions.ResourceNotFoundException;
import com.marpe.cht.repositories.OrderRepository;
import com.marpe.cht.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@Service
public class OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	DateTimeFormatter dtfmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Cacheable("orders")
	public Page<Order> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service to findAll Orders");
		PageRequest pageRequest = PageRequest.of(
            paginationRequest.getPage(),
            paginationRequest.getSize(),
            Sort.by(Sort.Direction.DESC, paginationRequest.getSortField()));
		Page<Order> orderPage = orderRepository.findAll(pageRequest);
        return new PageImpl<Order>(orderPage.getContent(), orderPage.getPageable(), orderPage.getTotalElements());
	}
	
	public Order findById(Long id) {
		log.info("Executing service to findById an Order with param: id={}", id); 
		return orderRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
	}
	
    @Transactional
	public Order create(Order request) {
		log.info("Executing service to create an Order with param: order={}", request);
		try {
			Order order = new Order();
			order.setCliente(request.getCliente());
			order.setRegional(request.getRegional());
			order.setCoordenador(request.getCoordenador());
			order.setDataInicio(request.getDataInicio());
			order.setHoraInicio(request.getHoraInicio());
			order.setObservacao(request.getObservacao());
			order.setTodosPagos(request.getTodosPagos());
			order.setConcluida(request.getConcluida());
			order.setState(Datastate.ACTIVE);
	        return orderRepository.save(order);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating Order input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error registering Order input data in database: " + e.getMessage());
		}
	}
    
    @Transactional
	public Order update(Long id, Order request) {
		log.info("Executing service to update an Order with params: id={} and order={}", id, request);
		try {
			Order order = findById(id);
			order.setCliente(request.getCliente());
			order.setRegional(request.getRegional());
			order.setCoordenador(request.getCoordenador());
			order.setDataInicio(request.getDataInicio());
			order.setHoraInicio(request.getHoraInicio());
			order.setObservacao(request.getObservacao());
			order.setTodosPagos(request.getTodosPagos());
			order.setConcluida(request.getConcluida());
			return orderRepository.save(order);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Error validating Cliente input data: {}", e.getConstraintViolations());
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Error updating Cliente input data in database: " + e.getMessage());
		}
	}

	@Transactional
	public String delete(Long id) {
		log.info("Executing service to delete an Order with param: id={}", id);
		try {
			orderRepository.deleteById(id);
			return "Registro removido com sucesso.";
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Order not found with id: " + id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
}
