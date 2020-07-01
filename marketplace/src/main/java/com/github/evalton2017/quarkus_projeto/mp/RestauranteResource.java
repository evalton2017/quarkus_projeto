package com.github.evalton2017.quarkus_projeto.mp;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.evalton2017.quarkus_projeto.mp.dto.PratoDTO;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

@Path("/restaurantes")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class RestauranteResource {

	@Inject
	PgPool client;
	
	@GET
	@Path("{idRestaurante}/pratos")
	public Multi<PratoDTO> buscarPratos(@PathParam("idRestaurante") Long idRestaurate){
		return Prato.findByRestaurante(client, idRestaurate);
	}
	
}
