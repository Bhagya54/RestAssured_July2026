package org.example.Day2_NonBDDStyle;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Get_NonBDDStyle {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
////https://api.zippopotam.us/IN/110001
    @Description("Positive Scenario - NonBDD Style - Valid Zipcode")
    @Test
    public void Get_NonBDDStyle(){
        req=given();
        req.baseUri("https://api.zippopotam.us");
        req.basePath("/IN/110001");

        res=req.when().log().all().get();

        vRes=res.then().log().all();
        vRes.statusCode(200);
    }

    @Description("Negative Scenario - NonBDD Style - Valid Zipcode")
    @Test
    public void Get_NonBDDStyleNegative(){
        req=given();
        req.baseUri("https://api.zippopotam.us");
        req.basePath("/IN/-1");

        res=req.when().log().all().get();

        vRes=res.then().log().all();
        vRes.statusCode(404);
    }
}
