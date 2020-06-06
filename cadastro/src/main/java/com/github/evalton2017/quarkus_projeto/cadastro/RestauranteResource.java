package com.github.evalton2017.quarkus_projeto.cadastro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import dto.AdicionaRestauranteDTO;
import dto.AtualizarRestauranteDTO;
import dto.RestauranteDTO;
import dto.RestauranteMappere;


@Path("/restaurantes")
public class RestauranteResource {
	
	@Inject
	RestauranteMappere restauranteMapper;
	
    @GET
    @Tag(name="Restaurante")
    public List<Restaurante> lista() {
    	Stream<Restaurante> restaurantes = Restaurante.streamAll();
    	return restaurantes.map(r-> restauranteMapper.toRestauranteDTO(r)).collect(Collectors.toList());
    }
    
    @POST
    @Transactional
    @Tag(name="Restaurante")
    public Response salvar(AdicionaRestauranteDTO dto) {
    	Restaurante restaurante = restauranteMapper.toRestaurante(dto);
    	restaurante.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    @Tag(name="Restaurante")
    public void atualizar(@PathParam("id") Long id, AtualizarRestauranteDTO dto) {
    	Optional<Restaurante>restauranteOp = Restaurante.findByIdOptional(id);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException();
    	}
    	Restaurante restaurante = restauranteOp.get();
    	
    	restauranteMapper.toRestaurante(dto, restaurante);
    	
    	restaurante.persist();
    }
    

    @DELETE
    @Path("{id}")
    @Transactional
    @Tag(name="Restaurante")
    public void delete(@PathParam("id") Long id) {
    	Optional<Restaurante>restauranteOp = Restaurante.findByIdOptional(id);
    
    	restauranteOp.ifPresentOrElse(Restaurante::delete, ()->{
     		throw new NotFoundException();
    	});
    	
        
    }
    
    /*END PONT PRATOS*/
    
    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name="Prato")
    public List<Restaurante> buscarPratos(@PathParam("idRestaurante") Long idRestaurante){
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	//List<Prato> lista = new ArrayList<Prato>();
    	return  Prato.list("restaurante", restauranteOp.get());
    	//return lista;
    }
    
    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
    @Tag(name="Prato")
    public Response adicionarPrato(@PathParam("idRestaurante")Long idRestaurante, Prato dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	
    	Prato prato = new Prato();
    	prato.nome= dto.nome;
    	prato.descricao = dto.descricao;
    	
    	prato.preco = dto.preco;
    	prato.restaurante = restauranteOp.get();
    	prato.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name="Prato")
    public void atualizar(@PathParam("idRestaurante")Long idRestaurante,@PathParam("id") Long id, Prato dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	
    	Optional<Prato> pratoOp = Restaurante.findByIdOptional(id);
    	
    	if(pratoOp.isEmpty()) {
    		throw new NotFoundException("Prato não existe");
    	}   	
    	Prato prato = pratoOp.get();    	
    	prato.preco = dto.preco;
    	prato.persist();

    }
    
    @DELETE
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name="Prato")
    public void delete(@PathParam("idRestaurante")Long idRestaurante,@PathParam("id") Long id, Prato dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	
    	Optional<Prato> pratoOp = Restaurante.findByIdOptional(id);
 	
    	pratoOp.ifPresentOrElse(Prato::delete, ()->{
    		throw new NotFoundException();
    	});
    }
    
}


