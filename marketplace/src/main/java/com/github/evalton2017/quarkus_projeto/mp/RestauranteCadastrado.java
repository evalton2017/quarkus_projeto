package com.github.evalton2017.quarkus_projeto.mp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.vertx.mutiny.pgclient.PgPool;

@ApplicationScoped
public class RestauranteCadastrado {
	
	@Inject
	PgPool client;
	

	@Incoming("restaurantes")
	public void receberRestaurante(String json) {
		Jsonb create = JsonbBuilder.create();
		Restaurante restaurante = create.fromJson(json, Restaurante.class);
		
		System.out.println("----------------------");
		System.out.println(json);
		System.out.println("----------------------");
		System.out.println(restaurante);
		
		restaurante.persist(client);
	}
}
