package org.example.Day2_NonBDDStyle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Post_NonBDDStyle {
RequestSpecification req;
Response res;
ValidatableResponse vRes;
    @Test
    public void createToken(){

        //https://restful-booker.herokuapp.com/auth
        //{
        //    "username" : "admin",
        //    "password" : "password123"
        //}
        //Content-Type: application/json'

        String payload="{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";
        req= given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/auth");
        req.contentType(ContentType.JSON);
        req.body(payload);

        res=req.when().log().all().post();

        vRes = res.then().log().all();
        vRes.statusCode(200);


    }
}
