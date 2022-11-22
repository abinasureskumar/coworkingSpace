package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Bookings;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import java.time.LocalDateTime;

@QuarkusTest
public class BookingsResource {

    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/bookings")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    public void testDeleteEndpoint() {
        var bookings = new Bookings();
        bookings.setBookedAt(LocalDateTime.now());
        bookings.setStartDate(LocalDateTime.now());
        bookings.setEndDate(LocalDateTime.now());
        bookings.setIsAccepted(true);

        given().contentType(ContentType.JSON).body(bookings).post("/bookings");
        given().when().delete("/bookings/1").then().statusCode(204);
    }

    @Test
    public void testPostEndpoint() {
        var bookings = new Bookings();
        bookings.setBookedAt(LocalDateTime.now());
        bookings.setStartDate(LocalDateTime.now());
        bookings.setEndDate(LocalDateTime.now());
        bookings.setIsAccepted(true);

        given().contentType(ContentType.JSON).body(bookings).post("/bookings").then().statusCode(200);
    }

    @Test
    public void testPutEndpoint() {
        var bookings = new Bookings();
        bookings.setBookedAt(LocalDateTime.now());
        bookings.setStartDate(LocalDateTime.now());
        bookings.setEndDate(LocalDateTime.now());
        bookings.setIsAccepted(true);

        var response = given().contentType(ContentType.JSON).body(bookings).post("/bookings");

        var newBookings = new Bookings();
        var id = response.jsonPath().get("id");
        newBookings.setBookedAt(LocalDateTime.now());
        newBookings.setStartDate(LocalDateTime.now());
        newBookings.setEndDate(LocalDateTime.now());
        newBookings.setIsAccepted(true);
        given().when().contentType(ContentType.JSON).body(newBookings).put("/bookings/" + id).then().statusCode(200);
    }
}
