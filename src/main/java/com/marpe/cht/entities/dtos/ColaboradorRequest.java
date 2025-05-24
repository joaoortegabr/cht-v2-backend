package com.marpe.cht.entities.dtos;

import com.marpe.cht.entities.DadosBancarios;
import com.marpe.cht.entities.DadosPessoais;
import com.marpe.cht.entities.User;

public record ColaboradorRequest(
		Long id,
		User user,
		DadosPessoais dadosPessoais,
		DadosBancarios dadosBancarios,
		String cidade
		) {

}
