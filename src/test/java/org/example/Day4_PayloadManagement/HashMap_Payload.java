package org.example.Day4_PayloadManagement;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static io.restassured.RestAssured.given;

public class HashMap_Payload {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;


    @Test
    public void createBooking_TestNGAss(){
        HashMap<String,Object> payload = new LinkedHashMap<>();
        payload.put("firstname","John");
        payload.put("lastname","K");
        payload.put("totalprice",345);
        payload.put("depositpaid",true);
        HashMap<String,Object> bookingDates = new LinkedHashMap<>();
        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout","2019-01-01");
        payload.put("bookingdates",bookingDates);
        payload.put("additionalneeds","breakfast");

        req=given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking");
        req.contentType(ContentType.JSON);
        req.body(payload);

        res=req.log().all().when().post();

        vRes=res.then().log().all();

        int bookingId=vRes.extract().path("bookingid");
        String fname=vRes.extract().path("booking.firstname");
        String checkin=vRes.extract().path("booking.bookingdates.checkin");
        int statusCode=vRes.extract().statusCode();
        String contentType = vRes.extract().contentType();

        Assert.assertNotNull(bookingId);
        Assert.assertEquals(fname,"John");
        Assert.assertEquals(checkin,"2018-01-01");
        Assert.assertEquals(statusCode,200);
        Assert.assertTrue(contentType.contains("application/json"));

    }

}
