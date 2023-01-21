package tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class GPathTests {
	private RequestSpecification requestSpec;
	private static String bookingId = "";

	@Before
	public void createRequestSpecification() {
		
		requestSpec = new RequestSpecBuilder().
			setBaseUri("https://restful-booker.herokuapp.com").
			build();
		
		//create a booking that can be verified
		if(bookingId.isEmpty()) createBooking();
	}
	
	@Test
	public void checkName_withGpath() {
				
		given().
			spec(requestSpec).
		when().
			get("/booking/" + bookingId).
		then().
			assertThat().
			body("firstname", equalTo("John"));
	}
	
	@Test
	public void checkDate_withGpath() {

		given().
		    spec(requestSpec).
		when().
			get("/booking/" + bookingId).
		then().
			assertThat().
			body("bookingdates.checkin", equalTo("2023-01-01"));
	}
	
	// uses createBooking.json file to create the booking.
	private void createBooking() {
		URL resource = getClass().getClassLoader().getResource("createBooking.json");
		File file = null;
		try {
			file = new File(resource.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		bookingId =
		given().
			spec(requestSpec).
			header("Content-Type","application/json").
			body(file).
		when().
			post("/booking").
		then().
			assertThat().
			statusCode(200).
			extract().
			path("bookingid").
			toString();
		
		System.out.println("Booking ID is: " + bookingId);
	}

}
