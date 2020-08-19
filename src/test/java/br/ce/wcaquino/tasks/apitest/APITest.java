package br.ce.wcaquino.tasks.apitest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.http.ContentType;

public class APITest {

	@BeforeClass
	public static void setup() {
		baseURI = "http://localhost:8001/tasks-backend";
	}
	
	@Test
	public void deveRetornarTarefas() {
		
		given()
		.when()
			.get("/todo")
		.then()
			.statusCode(200)
		;
	}
	
	@Test
	public void deveAdicionarTarefaComSucesso() {
		
		given()
			.body("{\"task\": \"Teste via API\",\"dueDate\": \"2020-08-20\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.log().all()
			.statusCode(201)
		;
	}
	
	@Test
	public void naoDeveAdicionarTarefaInvalida() {
		
		given()
			.body("{\"task\": \"Teste via API\",\"dueDate\": \"2010-08-20\"}")
			.contentType(ContentType.JSON)
		.when()
			.post("/todo")
		.then()
			.log().all()
			.statusCode(400)
			.body("message", is("Due date must not be in past"))
		;
	}
}



