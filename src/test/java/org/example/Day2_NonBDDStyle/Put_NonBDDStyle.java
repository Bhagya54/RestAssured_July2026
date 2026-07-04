package org.example.Day2_NonBDDStyle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Put_NonBDDStyle {
    //getToken()
    //getBookingId - createBooking

    //https://restful-booker.herokuapp.com/booking/:id
    //'Content-Type: application/json'
    //'Cookie: token=abc123'
    //body: {
    //    "firstname" : "James",
    //    "lastname" : "Brown",
    //    "totalprice" : 111,
    //    "depositpaid" : true,
    //    "bookingdates" : {
    //        "checkin" : "2018-01-01",
    //        "checkout" : "2019-01-01"
    //    },
    //    "additionalneeds" : "Breakfast"
    //}
    RequestSpecification req;
    Response res;
    ValidatableResponse vRes;
    String token;
    int bookinId;
    //String/HashMap/POJO class - serialization- json - java
    @Test
    public void updateBooking(){
        String payload="{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Brown\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        token="55924170ca1676d";
        bookinId=4436;
        req= given();
        req.baseUri("https://restful-booker.herokuapp.com");
        req.basePath("/booking/"+bookinId);
        req.contentType(ContentType.JSON);
        req.body(payload);
        req.cookie("token",token);

        res=req.when().log().all().put();

        vRes = res.then().log().all();
        vRes.statusCode(200);
    }
}
