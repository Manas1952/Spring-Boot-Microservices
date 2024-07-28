package com.manas.productServices;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.shaded.org.hamcrest.Matchers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MicroservicesApplicationTests {

	@ServiceConnection // this will automatically inject mongodb connection uri from application.properties
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

	@LocalServerPort
	private Integer port;

	static {
		mongoDBContainer.start();
	}

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
                {
                    "name": "Manas",
                    "description": "desc",
                    "price": 1234
                }
                """;

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/product")
				.then()
				.statusCode(201)
				.body("id", (Matcher<?>) Matchers.notNullValue())
				.body("name", (Matcher<?>) Matchers.equalTo("Manas"))
				.body("description", (Matcher<?>) Matchers.equalTo("desc"))
				.body("price", (Matcher<?>) Matchers.equalTo("1234"));
	}

}
