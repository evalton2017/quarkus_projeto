package br.quarkus_projeto.cadastro;

import java.util.List;
import java.util.Optional;

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

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import br.quarkus_projeto.cadastro.prato.Prato;
import br.quarkus_projeto.cadastro.prato.PratoDTO;
import br.quarkus_projeto.cadastro.prato.PratoMapper;
import infra.ConstraintViolationResponse;


@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name="Restaurante")
//@RolesAllowed("proprietario")
//@SecurityScheme(securitySchemeName = "projeto-oauth", type= SecuritySchemeType.OAUTH2, 
//flows=@OAuthFlows(password =@OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/quarkus_projeto/protocol/openid-connect/token")))
//@SecurityRequirement(name="projeto-oauth")
public class RestauranteResource {
	
	@Inject
	RestauranteMapper restauranteMapper;
	
	@Inject
	RestauranteRepository resutaranteRepository;
	
	@Inject
	PratoMapper pratoMapper;
	
    @GET
    @Tag(name="Restaurante")
    @Timed(name="Tempo completo de busca")
    @SimplyTimed(name="tempo simples de busca")
    @Counted(name="Quantidade de busca restaurante")
    public List<RestauranteDTO> lista() {    	
    	List<Restaurante> restaurantes = resutaranteRepository.findAll().list();
    	return restauranteMapper.toDtoList(restaurantes);
    }
    
    @POST
    @Transactional
    @APIResponse(responseCode = "201", description = "Restaurante incluido com sucesso")
    @APIResponse(responseCode = "400", content=@Content(schema=@Schema(allOf = ConstraintViolationResponse.class)))
    public Response salvar(RestauranteDTO dto) {
    	Restaurante restaurante = restauranteMapper.toEntity(dto);
    	restaurante.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public void atualizar(@PathParam("id") Long id, RestauranteDTO dto) {
    	Optional<Restaurante>restauranteOp = Restaurante.findByIdOptional(id);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException();
    	}
    	Restaurante restaurante = restauranteOp.get();
    	
    	restauranteMapper.updateModel(dto,restaurante);
    	
    	restaurante.setNome(dto.getNome());
    	
    	restaurante.persist();
    }
    

    @DELETE
    @Path("{id}")
    @Transactional
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
    public Response adicionarPrato(@PathParam("idRestaurante")Long idRestaurante, PratoDTO dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante não existe");
    	}
    	
    	Prato prato = pratoMapper.toEntity(dto);
    	prato.setRestaurante(restauranteOp.get());
    	prato.persist();
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
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
    	prato.setPreco(dto.getPreco());
    	prato.persist();

    }
    
    @DELETE
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
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


