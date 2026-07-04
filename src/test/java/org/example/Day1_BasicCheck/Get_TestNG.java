package org.example.Day1_BasicCheck;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Get_TestNG {
    String zipCode=null;
    @Owner("Bhagya")
    @Description("Validate Positive ZipCode Scenario - 560087")
    @Test
    public void getZipCode_Positive(){
        zipCode="560087";
        given()
                .baseUri("https://zippopotam.us/")
                .basePath("/IN/"+zipCode)
        .when()
                .get()
        .then()
                .statusCode(200)
                .body("places[0].state",equalTo("Karnataka"));
    }
    @Owner("Bhagya")
    @Description("Validate Negative ZipCode Scenario - -1")
    @Test
    public void getZipCode_Negative(){
        zipCode="-1";
        given()
                .baseUri("https://zippopotam.us/")
                .basePath("/IN/"+zipCode)
        .when()
                .get()
        .then()
                .statusCode(404);
    }
}
