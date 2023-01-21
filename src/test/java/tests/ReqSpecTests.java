package tests;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class ReqSpecTests {
	private RequestSpecification requestSpec;

	@Before
	public void createRequestSpecification() {
		
		requestSpec = new RequestSpecBuilder().
			setBaseUri("https://restful-booker.herokuapp.com").
			build();
	}
	
	@Test
	public void basicGet_withReqSpec() {
				
		given().
			spec(requestSpec).
		when().
			get("/booking").
		then().
			assertThat().
			statusCode(200);
	}

}
