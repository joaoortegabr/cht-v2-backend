package com.marpe.cht.entities.dtos;

import com.marpe.cht.entities.enums.Role;

public record UserRequest(
		Long id,
		String username,
		String password,
		Role role
		) {

}
