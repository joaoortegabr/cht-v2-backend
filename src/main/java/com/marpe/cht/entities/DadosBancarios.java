package com.marpe.cht.entities;

import java.io.Serializable;
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_dados_bancarios")
@SQLDelete(sql = "UPDATE tb_dados_bancarios SET state = '0' WHERE id = ?")
@SQLRestriction(value = "state = '1'")
public class DadosBancarios implements Serializable {
	private static final long serialVersionUID = -4752466686437172291L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private String banco;
	private String agencia;
	private String conta;
	private String operacao;
	private String titular;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Datastate state;
	
	public DadosBancarios() {
	}

	public DadosBancarios(User user, String banco, String agencia, String conta, String operacao, String titular) {
		this.user = user;
		this.banco = banco;
		this.agencia = agencia;
		this.conta = conta;
		this.operacao = operacao;
		this.titular = titular;
		this.state = Datastate.ACTIVE;
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

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
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
		DadosBancarios other = (DadosBancarios) obj;
		return Objects.equals(id, other.id);
	}

}
