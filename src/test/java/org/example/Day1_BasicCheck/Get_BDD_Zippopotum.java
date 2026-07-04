package org.example.Day1_BasicCheck;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class Get_BDD_Zippopotum {
    static String zipCode=null;
    public static void main(String[] args) {
        //https://api.zippopotam.us/IN/110001
        zipCode="110001";

        given()
                .baseUri("https://api.zippopotam.us")
                .basePath("/IN/"+zipCode)
        .when()
                .log().all().get()
        .then()
                .statusCode(200)
                .body("country",equalTo("India"))
                .body("places[0].state",equalTo("New Delhi"))
                .body("places[1]['place name']",equalTo("Rail Bhawan"))
                .body("places['place name']",hasItem("Sansadiya Soudha"))
                .body("places['place name']",hasSize(20))
                .time(lessThan(5000L))
                .header("Content-Type","application/json")
                .log().all();
                //key[0].key
                //key[0][""]

    }
}
