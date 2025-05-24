package com.marpe.cht.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.marpe.cht.entities.enums.Datastate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_coordenador")
@SQLDelete(sql = "UPDATE tb_coordenador SET state = '0' WHERE id = ?")
@SQLRestriction(value = "state = '1'")
public class Coordenador implements Serializable {
	private static final long serialVersionUID = -3213754637116158798L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "user_id")
	private User user;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "dados_pessoais")
	private DadosPessoais dadosPessoais;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "dados_bancarios")
	private DadosBancarios dadosBancarios;
	
	@OneToMany(mappedBy = "coordenador")
	private List<Order> orders;
	
    @Enumerated(EnumType.ORDINAL)
    private Datastate state;
	
	public Coordenador() {
	}

	public Coordenador(User user, DadosPessoais dadosPessoais, DadosBancarios dadosBancarios) {
		this.user = user;
		this.dadosPessoais = dadosPessoais;
		this.dadosBancarios = dadosBancarios;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DadosPessoais getDadosPessoais() {
		return dadosPessoais;
	}

	public void setDadosPessoais(DadosPessoais dadosPessoais) {
		this.dadosPessoais = dadosPessoais;
	}

	public DadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(DadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	public Datastate getState() {
		return state;
	}

	public void setState(Datastate state) {
		this.state = state;
	}

	public List<Order> getOrders() {
		return orders;
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
		Coordenador other = (Coordenador) obj;
		return Objects.equals(id, other.id);
	}

}
