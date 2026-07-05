package org.example.Day3_Assertions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssured_HamcrestAssertion {

    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
//https://restful-booker.herokuapp.com/booking
    //Content-Type: application/json'
    //{
//    "firstname" : "Jim",
//    "lastname" : "Brown",
//    "totalprice" : 111,
//    "depositpaid" : true,
//    "bookingdates" : {
//        "checkin" : "2018-01-01",
//        "checkout" : "2019-01-01"
//    },
//    "additionalneeds" : "Breakfast"
//}

    @Test
    public void createBooking(){
        String payload="{\n" +
                "    \"firstname\" : \"Jim\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
    req=given();
    req.baseUri("https://restful-booker.herokuapp.com");
    req.basePath("/booking");
    req.contentType(ContentType.JSON);
    req.body(payload);

    res=req.log().all().when().post();

    vRes=res.then().log().all();
    vRes.statusCode(200);
    vRes.body("bookingid",notNullValue());
    vRes.body("booking.firstname",equalTo("Jim"));
    vRes.body("booking.lastname",containsString("Br"));
    vRes.header("Content-Type","application/json; charset=utf-8");
    vRes.time(lessThan(5000L));
    }

}
