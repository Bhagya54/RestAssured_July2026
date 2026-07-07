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

public class POJO_Payload {

    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;

//Gson - serialization - toJson() - fromJSON()
    @Test
    public void createBooking_TestNGAss(){
       CreateBookingPOJO createBooking = new CreateBookingPOJO();
       createBooking.setFirstname("abc");
        createBooking.setLastname("xyz");
        createBooking.setDepositpaid(true);
        createBooking.setTotalprice(100);
        BookingDates bDate=new BookingDates();
        bDate.setCheckin("2018-01-01");
        bDate.setCheckout("2019-01-01");
        createBooking.setBookingdates(bDate);
        createBooking.setAdditionalneeds("lunch");

        req=given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking");
        req.contentType(ContentType.JSON);
        req.body(createBooking);

        res=req.log().all().when().post();

        vRes=res.then().log().all();

        int bookingId=vRes.extract().path("bookingid");
        String fname=vRes.extract().path("booking.firstname");
        String checkin=vRes.extract().path("booking.bookingdates.checkin");
        int statusCode=vRes.extract().statusCode();
        String contentType = vRes.extract().contentType();

        Assert.assertNotNull(bookingId);
        Assert.assertEquals(fname,"abc");
        Assert.assertEquals(checkin,"2018-01-01");
        Assert.assertEquals(statusCode,200);
        Assert.assertTrue(contentType.contains("application/json"));

    }
}
