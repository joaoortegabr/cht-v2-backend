package com.marpe.cht.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marpe.cht.entities.Order;
import com.marpe.cht.entities.dtos.OrderRequest;
import com.marpe.cht.entities.dtos.OrderResponse;

@Mapper(componentModel = "spring")
public interface OrderMapper {

	@Mapping(target = "atividades", ignore = true)
	@Mapping(target = "state", ignore = true)
	Order toOrder(OrderRequest orderRequest);
    
	OrderResponse toOrderResponse(Order order);
}
