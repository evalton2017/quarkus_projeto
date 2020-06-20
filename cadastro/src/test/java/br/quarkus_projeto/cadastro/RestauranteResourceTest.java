package br.quarkus_projeto.cadastro;

import javax.ws.rs.core.Response.Status;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;

import br.quarkus_projeto.cadastro.util.TokenUtils;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestauranteResourceTest {
	
	private String token;
	
	@BeforeEach	
	public void gereToken() throws Exception {
		token = TokenUtils.generateTokenString("/JWTProprietarioClaims.json", null);
	}

	/*Teste listar Restaurante*/
	@org.junit.jupiter.api.Test
	@DataSet("restaurantes-cenario-1.yml")
	public void testBuscarRestaurante() {
		String resultado = given()
			.when().get("/restaurantes")
			.then()
			.statusCode(Status.OK.getStatusCode())
			.extract().asString();
		Approvals.verifyJson(resultado);
	}
	
	private RequestSpecification given() {
		return RestAssured.given()
				.contentType(ContentType.JSON).header(new Header("Authorization","Bearer"+token));
	}
	
	/*Teste alterar Restaurante*/
	@Test
	@DataSet("restaurantes-cenario-1.yml")
	public void testAlterarRestaurante() {
		Restaurante dto = new Restaurante();
		dto.setNome("novoNome");
		Long parameterValue = 123L;
		given()
			.with().pathParam("id", parameterValue)
			.body(dto)
			.when().put("/restaurantes/{id}")
			.then()
			.statusCode(Status.NO_CONTENT.getStatusCode())
			.extract().asString();
		
		Restaurante findById = Restaurante.findById(parameterValue);
		
		Assert.assertEquals(dto.getNome(), findById.getNome());
	}
	
	
	
}
