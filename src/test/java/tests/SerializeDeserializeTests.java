package tests;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import data.Booking;
import data.BookingDate;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class SerializeDeserializeTests {
	private RequestSpecification requestSpec;
	private static String bookingId = "";

	@Before
	public void createRequestSpecification() {
		
		requestSpec = new RequestSpecBuilder().
			setBaseUri("https://restful-booker.herokuapp.com").
			build();
	}
	
    @Test
    public void createBooking_withSerializedBody() {
        
        bookingId =
        given().
            spec(requestSpec).
            header("Content-Type","application/json").
        and().
            body(getBookingInfo()).
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
    
    @Test
    public void getBooking_intoDeserializedClass() {
    	createBooking_withSerializedBody();

        Booking booking =
            given().
                spec(requestSpec).
            when().
                get("/booking/" + bookingId).
            then()
            	.extract().
            	response().
                as(Booking.class);

        assertEquals("Elon", booking.getFirstname());
    }
    
    private Booking getBookingInfo() {
    	
    	BookingDate date = new BookingDate();
    	date.setCheckin("2023-01-01");
    	date.setCheckout("2023-01-04");
    	
    	Booking booking = new Booking();
    	booking.setFirstname("Elon");
    	booking.setLastname("Musk");
    	booking.setTotalprice(500);
    	booking.setBookingdates(date);
    	booking.setDepositpaid(false);
    	booking.setAdditionalneeds("None");
    	
    	return booking;
    }

}
