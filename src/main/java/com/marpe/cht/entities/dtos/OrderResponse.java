package com.marpe.cht.entities.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marpe.cht.entities.Cliente;
import com.marpe.cht.entities.Coordenador;
import com.marpe.cht.entities.Regional;

public record OrderResponse(
		Long id,
		Cliente cliente,
		Regional regional,
		Coordenador coordenador,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
		LocalDate dataInicio,
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
		LocalTime horaInicio,
		String observacao,
		Boolean todosPagos,
		Boolean concluida
		) {

}
