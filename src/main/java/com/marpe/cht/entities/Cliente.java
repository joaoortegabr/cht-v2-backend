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
@Table(name = "tb_cliente")
@SQLDelete(sql = "UPDATE tb_cliente SET state = '0' WHERE id = ?")
@SQLRestriction(value = "state = '1'")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 3860635340174545351L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cidade;
	
	@OneToMany(mappedBy = "cliente")
	private List<Order> orders;
	
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Datastate state;
	
	public Cliente() {
	}

	public Cliente(String nome, String cidade) {
		this.nome = nome;
		this.cidade = cidade;
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}

}
