package com.github.evalton2017.quarkus_projeto.mp;

import java.math.BigDecimal;

import com.github.evalton2017.quarkus_projeto.mp.dto.PratoDTO;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

import java.util.stream.StreamSupport;


public class Prato {
	
	private Long usuario;
	
	private String nome;
	
	private String descricao;
	
	private Restaurante restaurante;
	
	private BigDecimal preco;

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public static Multi<PratoDTO> findAll(PgPool pgPool) {
		Uni<RowSet<Row>> preparedQuery = pgPool.preparedQuery("select * from prato").execute();
		return uniToMulti(preparedQuery);
	}
		
	public static Multi<PratoDTO> findByRestaurante(PgPool client, Long idRestaurante) {
		Uni<RowSet<Row>> preparedQuery = client
				.preparedQuery("SELECT * FROM prato where prato.restaurante_id = $1 ORDER BY nome ASC")
				.execute(Tuple.of(idRestaurante));
		return uniToMulti(preparedQuery);
	}


	private static Multi<PratoDTO> uniToMulti(Uni<RowSet<Row>> queryResult){
		return queryResult.onItem()
				.produceMulti(set-> Multi.createFrom().items(()->{
					 return StreamSupport.stream(set.spliterator(), false);
				}))
				.onItem().apply(PratoDTO::from);
	}
	
	
	
	

}
