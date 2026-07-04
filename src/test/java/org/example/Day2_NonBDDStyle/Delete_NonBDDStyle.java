package org.example.Day2_NonBDDStyle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Delete_NonBDDStyle {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    String token;
    int bookinId;
    //String/HashMap/POJO class - serialization- json - java
    @Test
    public void deleteBooking(){

        token="b6f727e1c6529c4";
        bookinId=3219;
        req= given();
        req.cookie("token",token);
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking/"+bookinId);


        res=req.when().log().all().delete();

        vRes = res.then().log().all();
        vRes.statusCode(201);
    }
}
