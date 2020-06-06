package com.github.evalton2017.quarkus_projeto.cadastro;

import java.util.HashMap;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class CadastroTestLifecycleManager implements QuarkusTestResourceLifecycleManager {

	private static PostgreSQLContainer<?> POSTGRESL = new PostgreSQLContainer<>("postgres:12.2");
	
	@Override
	public Map<String, String> start() {
		POSTGRESL.start();
		Map<String, String> propriedades = new HashMap<>();
		propriedades.put("quarkus.datasource.url", POSTGRESL.getJdbcUrl());
		propriedades.put("quarkus.datasource.username", POSTGRESL.getUsername());
		propriedades.put("quarkus.datasource.password", POSTGRESL.getPassword());
		return propriedades;
	}

	@Override
	public void stop() {
		if(POSTGRESL != null && POSTGRESL.isRunning()) {
			POSTGRESL.stop();
		}
		
	}

}
