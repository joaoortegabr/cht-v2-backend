package com.marpe.cht.entities.dtos;

public record RegionalResponse(
		Long id,
		String nome,
		Double horasPadrao,
		Double valorHoraDiurna,
		Double valorHoraNoturna,
		Double valorTransporte,
		String descricao
		) {

}
