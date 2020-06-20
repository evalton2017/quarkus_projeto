package br.quarkus_projeto.cadastro;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.quarkus_projeto.cadastro.prato.PratoMapper;
import tolls.AbstractMapper;

@Mapper(componentModel = "cdi", uses = {PratoMapper.class})
public interface RestauranteMapper extends AbstractMapper<Restaurante, RestauranteDTO> {

	
	@Mapping(target ="dataCriacao",dateFormat="dd/MM/yyy HH:mm:ss")
	public RestauranteDTO toDto(Restaurante r);
	

	
}
