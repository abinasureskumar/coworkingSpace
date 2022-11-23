package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Rooms;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class RoomsResourceTest {
    
    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/rooms")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    public void testDeleteEndpoint() {
        var rooms = new Rooms();
        rooms.setRoomNr("R048");

        given().contentType(ContentType.JSON).body(rooms).post("/rooms");
        given().when().delete("/rooms/1").then().statusCode(204);
    }

    @Test
    public void testPostEndpoint() {
        var rooms = new Rooms();
        rooms.setRoomNr("R048");

        given().contentType(ContentType.JSON).body(rooms).post("/rooms").then().statusCode(200);
    }

    @Test
    public void testPutEndpoint() {
        var rooms = new Rooms();
        rooms.setRoomNr("R048");

        var response = given().contentType(ContentType.JSON).body(rooms).post("/rooms");

        var newRoom = new Rooms();
        var id = response.jsonPath().get("id");
        newRoom.setRoomNr("M304");
        given().when().contentType(ContentType.JSON).body(newRoom).put("/rooms/" + id).then().statusCode(200);
    }
}
