package ch.zli.m223;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import ch.zli.m223.model.Users;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class UsersResourceTest {

    @Test
    public void testIndexEndpoint() {
        given()
          .when().get("/users")
          .then()
             .statusCode(200)
             .body(is("[]"));
    }

    @Test
    public void testDeleteEndpoint() {
        var users = new Users();
        users.setVorname("nina");
        users.setNachname("müller");
        users.setEmail("nina.müller@gamil.com");
        users.setPasswort("98765");
        users.setIsAdmin(true);

        given().contentType(ContentType.JSON).body(users).post("/users");
        given().when().delete("/users/1").then().statusCode(204);
    }

    @Test
    public void testUpdate() {
        var users = new Users();
        users.setVorname("nina");
        users.setNachname("müller");
        users.setEmail("nina.müller@gamil.com");
        users.setPasswort("98765");
        users.setIsAdmin(true);

        var response = given().contentType(ContentType.JSON).body(users).post("/users");
        var id = response.jsonPath().get("id");
        users.setVorname("markus");
        users.setNachname("kuster");
        users.setEmail("markus.kuster@gamil.com");
        users.setPasswort("12345");
        users.setIsAdmin(false);
        given().when().delete("/users/" + id).then().statusCode(204);
    }
}
