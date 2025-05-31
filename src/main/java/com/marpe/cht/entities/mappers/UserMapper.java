package com.marpe.cht.entities.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.marpe.cht.entities.User;
import com.marpe.cht.entities.dtos.UserRequest;
import com.marpe.cht.entities.dtos.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

	@Mapping(target = "authorities", ignore = true)
	@Mapping(target = "state", ignore = true)
	User toUser(UserRequest userRequest);
    
	UserResponse toUserResponse(User user);
}
