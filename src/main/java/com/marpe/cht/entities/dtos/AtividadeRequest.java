package com.marpe.cht.entities.dtos;

import java.time.LocalTime;

import com.marpe.cht.entities.Colaborador;
import com.marpe.cht.entities.Order;

public record AtividadeRequest(
		Long id,
		Order order,
		Colaborador colaborador,
		LocalTime horaInicial,
		LocalTime horaFinal,
		Boolean intervalo,
		Integer transportes,
		Boolean pago		
		) {

}
