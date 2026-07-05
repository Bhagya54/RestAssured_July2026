package org.example.Day3_Assertions;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.*;
public class AssertJ_Assertion {
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
                "    \"firstname\" : \"John\",\n" +
                "    \"lastname\" : \"Kap\",\n" +
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
        long responseTime=vRes.extract().time();
       assertThat(bookingId).isPositive().isNotNull().isNotZero();
       assertThat(fname).isEqualTo("John").isNotNull().isNotEmpty().isNotBlank().isAlphabetic();
        assertThat(responseTime).isLessThan(5000L);

       //empty - ""
        //blank - "    "
    }
}
