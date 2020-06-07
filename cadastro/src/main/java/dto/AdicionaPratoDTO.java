package dto;

import java.math.BigDecimal;

@validDTO
public class AdicionaPratoDTO  implements DTO{
		 
	 public String nome;
	 
	 public String descricao;
	 
	 public RestauranteDTO restaurante;
	 
	 public BigDecimal preco;

}
