package br.quarkus_projeto.cadastro.prato;

import org.mapstruct.Mapper;

import tolls.AbstractMapper;

@Mapper(componentModel = "cdi")
public interface PratoMapper extends AbstractMapper<Prato, PratoDTO>{

	
}
