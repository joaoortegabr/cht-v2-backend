package com.marpe.cht.entities.dtos;

public record RegionalRequest(
		String nome,
		Double horasPadrao,
		Double valorHoraDiurna,
		Double valorHoraNoturna,
		Double valorTransporte,
		String descricao
		) {

}
