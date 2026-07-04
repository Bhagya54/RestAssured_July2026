package org.example.Day1_BasicCheck;

import static io.restassured.RestAssured.*;

public class Get_BDD {
    public static void main(String[] args) {

                given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/booking/1")
                .when()
                    .get()
                .then()
                    .statusCode(200);
    }
}
