package com.marpe.cht.entities.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CidadeIBGEResponse {

    private int id;
    private String nome;
    private Microrregiao microrregiao;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Microrregiao getMicrorregiao() {
		return microrregiao;
	}
	public void setMicrorregiao(Microrregiao microrregiao) {
		this.microrregiao = microrregiao;
	}
	
	
	public static class Microrregiao {
	    private Mesorregiao mesorregiao;

		public Mesorregiao getMesorregiao() {
			return mesorregiao;
		}

		public void setMesorregiao(Mesorregiao mesorregiao) {
			this.mesorregiao = mesorregiao;
		}
	}
	

	public static class Mesorregiao {
		@JsonProperty("UF")
	    private UF uf;

		public UF getUf() {
			return uf;
		}

		public void setUF(UF uf) {
			this.uf = uf;
		}
	}

	
	public static class UF {
	    private String sigla;
	    private String nome;
	    
		public String getSigla() {
			return sigla;
		}
		public void setSigla(String sigla) {
			this.sigla = sigla;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
	}
    
}
