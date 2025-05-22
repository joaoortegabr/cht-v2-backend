package com.marpe.cht.entities;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.marpe.cht.entities.enums.Datastate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
	
@Entity
@Table(name = "tb_oscolab")
@SQLDelete(sql = "UPDATE tb_oscolab SET state = '0' WHERE id = ?")
@SQLRestriction(value = "state = '1'")
public class OSColab implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonFormat(pattern = "HH:mm")
	protected LocalTime horaInicial;
	@JsonFormat(pattern = "HH:mm")
	protected LocalTime horaFinal;
	protected Double totalHorasDiurnas;
	protected Double totalHorasNoturnas;
	@Column(name = "intervalo", nullable = false)
	protected Boolean intervalo;
	protected Integer transportes;
	protected Double totalAReceber;
	@Column(name = "pago", nullable = false)
	protected Boolean pago;
	
	@ManyToOne
	@JoinColumn(name = "colaborador_id")
	private Colaborador colaborador;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "os_id")
	private OS os;
	
    @Enumerated(EnumType.ORDINAL)
    private Datastate state;
	
	public OSColab() {
	}

	public OSColab(LocalTime horaInicial, LocalTime horaFinal, Boolean intervalo,
			Integer transportes, Boolean pago, Colaborador colaborador, OS os) {
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
		this.totalHorasDiurnas = 0.0;
		this.totalHorasNoturnas = 0.0;
		this.intervalo = false;
		this.transportes = transportes;
		this.totalAReceber = 0.0;
		this.pago = false;
		this.colaborador = colaborador;
		this.os = os;
		this.state = Datastate.ACTIVE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalTime getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(LocalTime horaInicial) {
		this.horaInicial = horaInicial;
	}
	
	public LocalTime getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(LocalTime horaFinal) {
		this.horaFinal = horaFinal;
	}
	
	public Double getTotalHorasDiurnas() {
		return totalHorasDiurnas;
	}

	public void setTotalHorasDiurnas(Double totalHorasDiurnas) {
		this.totalHorasDiurnas = totalHorasDiurnas;
	}
	
	public Double getTotalHorasNoturnas() {
		return totalHorasNoturnas;
	}

	public void setTotalHorasNoturnas(Double totalHorasNoturnas) {
		this.totalHorasNoturnas = totalHorasNoturnas;
	}

	public Boolean getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(Boolean intervalo) {
		this.intervalo = intervalo;
	}

	public Integer getTransportes() {
		return transportes;
	}

	public void setTransportes(Integer transportes) {
		this.transportes = transportes;
	}

	public Double getTotalAReceber() {
		return totalAReceber;
	}

	public void setTotalAReceber(Double totalAReceber) {
		this.totalAReceber = totalAReceber;
	}

	public Boolean getPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}
	
	public Colaborador getColaborador() {
		return colaborador;
	}
	
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}	
	
	public OS getOs() {
		return os;
	}
	
	public void setOs(OS os) {
		this.os = os;
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
		OSColab other = (OSColab) obj;
		return Objects.equals(id, other.id);
	}
	
}