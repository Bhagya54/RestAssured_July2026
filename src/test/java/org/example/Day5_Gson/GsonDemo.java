package org.example.Day5_Gson;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.Day5_Gson.BookingDates;
import static org.assertj.core.api.Assertions.*;
import org.example.Day4_PayloadManagement.CreateBookingPOJO;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GsonDemo {
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    Gson gson=new Gson();
    //Gson - serialization - toJson() - fromJSON()
    @Test
    public void createBooking_TestNGAss(){
        Booking createBooking = new Booking();
        createBooking.setFirstname("Jaya");
        createBooking.setLastname("Bachan");
        createBooking.setDepositpaid(true);
        createBooking.setTotalprice(100);
        BookingDates bDate=new BookingDates();
        bDate.setCheckin("2018-01-01");
        bDate.setCheckout("2019-01-01");
        createBooking.setBookingdates(bDate);
        createBooking.setAdditionalneeds("lunch");

        //serialization - java object > json string
        String jsonStringPayload=gson.toJson(createBooking);
        req=given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking");
        req.contentType(ContentType.JSON);
        req.body(jsonStringPayload);

        res=req.log().all().when().post();
        String responseString=res.asString();

        vRes=res.then().log().all();



        //1. First way of extraction - extract().path("");
        int bookingId=vRes.extract().path("bookingid");
        String fname=vRes.extract().path("booking.firstname");
        String checkin=vRes.extract().path("booking.bookingdates.checkin");
        int statusCode=vRes.extract().statusCode();
        String contentType = vRes.extract().contentType();

        //2. JsonPath class
        JsonPath jsonPath = new JsonPath(responseString);
        String lastName=jsonPath.getString("booking.lastname");
        System.out.println("Last name: " + lastName);

        //3. Using Deserialization
        //Deserialization - json string - java object
        BookingResponse  bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        String firstName=bookingResponse.getBooking().getFirstname();
        String lName=bookingResponse.getBooking().getLastname();
        int bID=bookingResponse.getBookingid();


        assertThat(bID).isNotNegative().isPositive().isNotZero().isNotNull();
        assertThat(firstName).isEqualTo("Jaya").isNotNull().isNotEmpty().isNotBlank();


    }
}
