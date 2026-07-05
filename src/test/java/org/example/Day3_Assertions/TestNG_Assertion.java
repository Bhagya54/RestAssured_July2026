package org.example.Day3_Assertions;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class TestNG_Assertion {
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
    public void createBooking_TestNGAss(){
        String payload="{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Bond\",\n" +
                "    \"totalprice\" : 123,\n" +
                "    \"depositpaid\" : false,\n" +
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

        int bookingId=vRes.extract().path("bookingid");
        String fname=vRes.extract().path("booking.firstname");
        String checkin=vRes.extract().path("booking.bookingdates.checkin");
        int statusCode=vRes.extract().statusCode();
        String contentType = vRes.extract().contentType();

        Assert.assertNotNull(bookingId);
        Assert.assertEquals(fname,"James");
        Assert.assertEquals(checkin,"2018-01-01");
        Assert.assertEquals(statusCode,200);
        Assert.assertTrue(contentType.contains("application/json"));

    }

}
