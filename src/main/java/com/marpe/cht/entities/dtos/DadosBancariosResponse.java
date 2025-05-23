package com.marpe.cht.entities.dtos;

import com.marpe.cht.entities.User;

public record DadosBancariosResponse(
		Long id,
		User user,
		String banco,
		String agencia,
		String conta,
		String operacao,
		String titular
		) {

}
