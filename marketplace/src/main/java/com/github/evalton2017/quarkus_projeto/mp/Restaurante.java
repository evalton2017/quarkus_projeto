package com.github.evalton2017.quarkus_projeto.mp;

import io.vertx.mutiny.sqlclient.Tuple;
import io.vertx.mutiny.pgclient.PgPool;

public class Restaurante {
	
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
	
	
	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nome=" + nome + ", localizacao=" + localizacao + "]";
	}
	
	public void persist(PgPool client) {		
		client.preparedQuery("insert into localizacao (id, latitude, longitude) values ($1, $2, $3)")
			.execute(Tuple.of(localizacao.getId(), localizacao.getLatitude(), localizacao.getLongitude())).await().indefinitely();
		
		client.preparedQuery("insert into restaurante (id, nome, localizacao_id) values ($1, $2, $3)")
		.execute(Tuple.of(this.id, this.nome, this.localizacao.getId())).await().indefinitely();
						
	}
	public void salvar(PgPool client, Restaurante restaurante) {
		Localizacao localizacao = restaurante.getLocalizacao();
		
		client.preparedQuery("insert into localizacao (id, latitude, longitude) values ($1, $2, $3)")
		.execute(Tuple.of(localizacao.getId(), localizacao.getLatitude(), localizacao.getLongitude())).await().indefinitely();
	
		client.preparedQuery("insert into restaurante (id, nome, localizacao_id) values ($1, $2, $3)")
		.execute(Tuple.of(this.id, this.nome, this.localizacao.getId())).await().indefinitely();
		
	}

	
	
	

}
