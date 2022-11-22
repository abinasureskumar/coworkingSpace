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
    public void testPostEndpoint() {
        var users = new Users();
        users.setVorname("nina");
        users.setNachname("müller");
        users.setEmail("nina.müller@gamil.com");
        users.setPasswort("98765");
        users.setIsAdmin(true);

        given().contentType(ContentType.JSON).body(users).post("/users").then().statusCode(200);
    }

    @Test
    public void testPutEndpoint() {
        var users = new Users();
        users.setVorname("nina");
        users.setNachname("müller");
        users.setEmail("nina.müller@gamil.com");
        users.setPasswort("98765");
        users.setIsAdmin(true);

        var response = given().contentType(ContentType.JSON).body(users).post("/users");

        var newUser = new Users();
        var id = response.jsonPath().get("id");
        newUser.setVorname("markus");
        newUser.setNachname("kuster");
        newUser.setEmail("markus.kuster@gamil.com");
        newUser.setPasswort("12345");
        newUser.setIsAdmin(false);
        given().when().contentType(ContentType.JSON).body(newUser).put("/users/" + id).then().statusCode(200);
    }
}
