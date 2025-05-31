package com.marpe.cht.entities.dtos;

import com.marpe.cht.entities.enums.Role;

public record UserResponse(
		Long id,
		String username,
		String password,
		Role role
		) {

}
