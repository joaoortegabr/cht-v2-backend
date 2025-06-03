package com.marpe.cht.entities.dtos;

public record UserPasswordRequest(
		Long id,
		String currentPassword,
		String newPassword
		) {

}
