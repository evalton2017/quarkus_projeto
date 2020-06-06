package dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.github.evalton2017.quarkus_projeto.cadastro.Restaurante;

@Mapper(componentModel = "cdi")
public interface RestauranteMappere {

	@Mapping(target = "nome", source="nome")
	@Mapping(target = "id",  ignore=true)
	@Mapping(target = "dataCriacao", ignore=true)
	@Mapping(target = "dataAtualizacao", ignore=true)
	@Mapping(target = "localizacao.id", ignore=true)
	public Restaurante toRestaurante(AdicionaRestauranteDTO dto);
	
	public void toRestaurante(AtualizarRestauranteDTO dto, @MappingTarget Restaurante restaurante);
	
	@Mapping(target="dataCriacao",dateFormat="dd/MM/yyy HH:mm:ss")
	public Restaurante toRestauranteDTO(Restaurante r);
	
	
	
}
