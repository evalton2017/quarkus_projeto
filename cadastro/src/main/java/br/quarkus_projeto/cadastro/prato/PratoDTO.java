package br.quarkus_projeto.cadastro.prato;

import java.math.BigDecimal;

import br.quarkus_projeto.cadastro.RestauranteDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PratoDTO {
	
	 private Long id;
	 
	 private String nome;
	 
	 private String descricao;
	 
	 private RestauranteDTO restaurante;
	 
	 private BigDecimal preco;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public RestauranteDTO getRestaurante() {
		return restaurante;
	}

	public void setRestaurante(RestauranteDTO restaurante) {
		this.restaurante = restaurante;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	 
	 
	

}
