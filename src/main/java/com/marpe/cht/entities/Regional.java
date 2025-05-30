package com.marpe.cht.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.marpe.cht.entities.enums.Datastate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_regional")
@SQLDelete(sql = "UPDATE tb_regional SET state = '0' WHERE id = ?")
@SQLRestriction(value = "state = '1'")
public class Regional implements Serializable {
	private static final long serialVersionUID = 6497944540716603408L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private Double horasPadrao;
	private Double valorHoraDiurna;
	private Double valorHoraNoturna;
	private Double valorTransporte;
	private String descricao;
	
	@OneToMany(mappedBy = "regional")
	private List<Order> orders;
	
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Datastate state;
	
	public Regional() {
	}

	public Regional(String nome, Double horasPadrao, Double valorHoraDiurna, Double valorHoraNoturna,
			Double valorTransporte, String descricao) {
		this.nome = nome;
		this.horasPadrao = horasPadrao;
		this.valorHoraDiurna = valorHoraDiurna;
		this.valorHoraNoturna = valorHoraNoturna;
		this.valorTransporte = valorTransporte;
		this.descricao = descricao;
		this.state = Datastate.ACTIVE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getHorasPadrao() {
		return horasPadrao;
	}

	public void setHorasPadrao(Double horasPadrao) {
		this.horasPadrao = horasPadrao;
	}

	public Double getValorHoraDiurna() {
		return valorHoraDiurna;
	}

	public void setValorHoraDiurna(Double valorHoraDiurna) {
		this.valorHoraDiurna = valorHoraDiurna;
	}

	public Double getValorHoraNoturna() {
		return valorHoraNoturna;
	}

	public void setValorHoraNoturna(Double valorHoraNoturna) {
		this.valorHoraNoturna = valorHoraNoturna;
	}

	public Double getValorTransporte() {
		return valorTransporte;
	}

	public void setValorTransporte(Double valorTransporte) {
		this.valorTransporte = valorTransporte;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Order> getOrders() {
		return orders;
	}
	
	public Datastate getState() {
		return state;
	}

	public void setState(Datastate state) {
		this.state = state;
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
		Regional other = (Regional) obj;
		return Objects.equals(id, other.id);
	}
	
}
