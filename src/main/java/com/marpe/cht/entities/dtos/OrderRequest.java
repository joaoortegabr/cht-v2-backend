package com.marpe.cht.entities.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.marpe.cht.entities.Cliente;
import com.marpe.cht.entities.Coordenador;
import com.marpe.cht.entities.Regional;

public record OrderRequest(
		Long id,
		Cliente cliente,
		Regional regional,
		Coordenador coordenador,
		LocalDate dataInicio,
		LocalTime horaInicio,
		String observacao,
		Boolean todosPagos,
		Boolean concluida
		) {

}
