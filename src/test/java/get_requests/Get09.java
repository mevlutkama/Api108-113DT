package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/246
        When
            I send GET Request to the url
        Then
            Response body should be like that;
            {
                "firstname": "Alex",
                "lastname": "Dominguez",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void get09(){
        // Set the Url
        spec.pathParams("first","booking","second",246);

        // Set the expected data
        Map<String, String> bookingDatesMap = new HashMap<>();
        bookingDatesMap.put("checkin", "2018-01-01");
        bookingDatesMap.put("checkout", "2019-01-01");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname","Alex");
        expectedData.put("lastname","Dominguez");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingDatesMap);
        expectedData.put("additionalneeds","Breakfast");

        // Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);// De-Serialization
        System.out.println("actualData: " + actualData);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        // 1.Way:
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));

        // 2.Way: Recommended
        assertEquals(bookingDatesMap.get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));


    }

    @Test
    public void get09b(){
        // Set the Url
        spec.pathParams("first","booking","second",246);

        // Set the expected data
        HerOkuAppTestData obj = new HerOkuAppTestData();
        Map<String,String> bookingDatesMap = obj.bookingdatesMapSetUp("2018-01-01","2019-01-01");
        Map<String,Object> expectedData = obj.expectedDataSetUp("Alex","Dominguez",111,true,bookingDatesMap,"Breakfast");
        System.out.println("expectedData: " + expectedData);

        // Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);// De-Serialization
        System.out.println("actualData: " + actualData);

        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));

        // 1.Way:
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(((Map)expectedData.get("bookingdates")).get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));

        // 2.Way: Recommended
        assertEquals(bookingDatesMap.get("checkin"), ((Map)(actualData.get("bookingdates"))).get("checkin"));
        assertEquals(bookingDatesMap.get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));


    }

}
