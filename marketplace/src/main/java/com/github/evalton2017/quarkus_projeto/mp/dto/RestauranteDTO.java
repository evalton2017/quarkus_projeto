package com.github.evalton2017.quarkus_projeto.mp.dto;

import java.io.Serializable;

import com.github.evalton2017.quarkus_projeto.mp.Localizacao;

public class RestauranteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private Localizacao localizacao;
	
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
	public Localizacao getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}
	

	
	
	
	
}
