package post_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post07 extends HerOkuAppBaseUrl {
     /*  POST Request: Creating a new data in database

    Given
        https://restful-booker.herokuapp.com/booking

          {                                             //outher json
                "firstname": "Selim",
                "lastname": "Ak",
                "totalprice": 11111,
                "depositpaid": true,
                "bookingdates": {                       //inner json
                    "checkin": "2021-09-09",
                    "checkout": "2021-09-21"
                },
                "additionalneeds": "Breakfast"
            }
    When
        I send POST Request to the Url
    Then
        Status code is 200
        And response body should be like {
            "bookingid": "6181",
            "booking":  {
            "firstname": "Selim",
            "lastname": "Ak",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                    "checkin": "2021-09-09",
                    "checkout": "2021-09-21"
            },
            "additionalneeds": "Breakfast"
        }

     */

    @Test
    public void post01(){

        //1.Step: Set the url
        spec.pathParam("first","booking");

        //2.Step: Set the expected data
        Map<String, String> expectedBookingdates = new HashMap<>();
        expectedBookingdates.put("checkin", "2021-09-09");
        expectedBookingdates.put("checkout", "2021-09-21");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Selim");
        expectedData.put("lastname", "Ak");
        expectedData.put("totalprice", 11111);
        expectedData.put("depositpaid", true);
        expectedData.put("expectedBookingdates", expectedBookingdates);
        expectedData.put("additionalneeds", "Breakfast");
        System.out.println(expectedData);

        //3.Step: Send the Post request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        //spec(spec).body() -->These two is pre-requisite, I write them before When()
        response.prettyPrint();


        //4.Step: Do assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);
//        Map<String, Object> actualData =  JsonUtils.convertJsonToJavaObject(response.asString(), HashMap.class);

        assertEquals(expectedData.get("firstname"), ((Map)actualData.get("booking")).get("firstname"));     //actualData.get("booking") is Object, I have to convert it to Map for access get() method
        assertEquals(expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map)actualData.get("booking")).get("depositpaid"));
        assertEquals(expectedBookingdates.get("checkin"), ((Map) ((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(expectedBookingdates.get("checkout"), ((Map) ((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
    }
}
