package dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.github.evalton2017.quarkus_projeto.cadastro.Prato;
import com.github.evalton2017.quarkus_projeto.cadastro.Restaurante;

@Mapper(componentModel = "cdi")
public interface PratoMappere {

	@Mapping(target = "nome", source="nome")
	@Mapping(target = "id",  ignore=true)
	@Mapping(target = "descricao", ignore=true)
	@Mapping(target = "preco", ignore=true)
	@Mapping(target = "restaurante.id", ignore=true)
	public Prato toPrato(AdicionaPratoDTO dto);
	
	public void toRestaurante(AtualizaPratoDTO dto, @MappingTarget Prato prato);
	
	public Prato toPratoDTO(Prato p);
	
	
	
}
