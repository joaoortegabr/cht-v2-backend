package com.marpe.cht.controllers;

import java.net.URI;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.marpe.cht.entities.Order;
import com.marpe.cht.entities.dtos.OrderRequest;
import com.marpe.cht.entities.dtos.OrderResponse;
import com.marpe.cht.entities.mappers.OrderMapper;
import com.marpe.cht.services.OrderService;
import com.marpe.cht.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	OrderMapper mapper = Mappers.getMapper(OrderMapper.class);

	private final OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<Page<OrderResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll Orders");
		Page<Order> orderPage = orderService.findAll(paginationRequest);
		Page<OrderResponse> orderResponsePage = orderPage.map(mapper::toOrderResponse);
	    return ResponseEntity.ok(orderResponsePage);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
		log.info("Receiving request to findById an Order with param: id={}", id);
		OrderResponse order = mapper.toOrderResponse(orderService.findById(id));
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping
	public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest request) {
		log.info("Receiving request to create an Order with param: order={}", request);
		Order order = mapper.toOrder(request);
		Order createdOrder = orderService.create(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdOrder.getId()).toUri();
		OrderResponse orderResponse = mapper.toOrderResponse(createdOrder);
		return ResponseEntity.created(uri).body(orderResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<OrderResponse> update(@PathVariable Long id, @RequestBody OrderRequest request) {
		log.info("Receiving request to update an Order with params: id={} and order={}", id, request);
		Order order = mapper.toOrder(request);
		Order updatedOrder = orderService.update(id, order);
		OrderResponse orderResponse = mapper.toOrderResponse(updatedOrder);
		return ResponseEntity.ok().body(orderResponse);
	}	
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("Receiving request to delete an Order with param: id={}", id);
		String msg = orderService.delete(id);
		return ResponseEntity.ok(msg);
	}	
	
}
