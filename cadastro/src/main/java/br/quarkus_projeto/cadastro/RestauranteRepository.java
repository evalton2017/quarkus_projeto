package br.quarkus_projeto.cadastro;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class RestauranteRepository implements PanacheRepository<Restaurante> {

}
