package com.marpe.cht.entities.dtos;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marpe.cht.entities.Colaborador;
import com.marpe.cht.entities.Order;

public record AtividadeResponse(
		Long id,
		Order order,
		Colaborador colaborador,
		@JsonFormat(pattern = "HH:mm")
		LocalTime horaInicial,
		@JsonFormat(pattern = "HH:mm")
		LocalTime horaFinal,
		Double totalHorasDiurnas,
		Double totalHorasNoturnas,
		Boolean intervalo,
		Integer transportes,
		Double adicionalViagem,
		Double totalAReceber,
		Boolean pago		
		) {

}
