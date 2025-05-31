package com.marpe.cht.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.marpe.cht.entities.enums.Datastate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order", indexes = {
	    @Index(name = "idx_coordenador_id", columnList = "coordenador_id")})
@SQLDelete(sql = "UPDATE tb_order SET state = '0' WHERE id = ?")
@SQLRestriction(value = "state = '1'")
public class Order implements Serializable {
	private static final long serialVersionUID = 5626482936468572904L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "regional_id")
	private Regional regional;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "colaborador_id")
	private Colaborador colaborador;
	private LocalDate dataInicio;
	private LocalTime horaInicio;
	private String observacao;
	@Column(name = "todos_pagos", nullable = false)
	private Boolean todosPagos;
	@Column(name = "concluida", nullable = false)
	private Boolean concluida;

	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Atividade> atividades;
	
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Datastate state;
	
	public Order() {
	}

	public Order(Cliente cliente, Regional regional, Colaborador colaborador,
			LocalDate dataInicio, LocalTime horaInicio, String observacao,
			Boolean todosPagos,	Boolean concluida) {
		this.cliente = cliente;
		this.regional = regional;
		this.colaborador = colaborador;
		this.dataInicio = dataInicio;
		this.horaInicio = horaInicio;
		this.observacao = observacao;
		this.todosPagos = todosPagos;
		this.concluida = concluida;
		this.state = Datastate.ACTIVE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getTodosPagos() {
		return todosPagos;
	}

	public void setTodosPagos(Boolean todosPagos) {
		this.todosPagos = todosPagos;
	}

	public Boolean getConcluida() {
		return concluida;
	}

	public void setConcluida(Boolean concluida) {
		this.concluida = concluida;
	}

	public Datastate getState() {
		return state;
	}

	public void setState(Datastate state) {
		this.state = state;
	}

	public Set<Atividade> getAtividades() {
		return atividades;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
		
}