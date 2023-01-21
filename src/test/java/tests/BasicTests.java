package tests;

import static io.restassured.RestAssured.given;

import org.junit.Test;

public class BasicTests {
	@Test
	public void basic_get_with200response() {

		given().
			baseUri("https://restful-booker.herokuapp.com").
		when().
			get("/ping").
		then().
			assertThat().
			statusCode(201);
	}
	
	@Test
	public void basic_get_with404response() {
				
		given().
			baseUri("https://restful-booker.herokuapp.com").
		when().
			get("/ping123").
		then().
			assertThat().
			statusCode(404);
	}

}
