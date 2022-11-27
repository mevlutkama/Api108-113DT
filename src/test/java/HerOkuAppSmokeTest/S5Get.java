package HerOkuAppSmokeTest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static HerOkuAppSmokeTest.S1Post.bookingid;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S5Get extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        User sends GET Request
    Then
        Status Code is 404
    And
        Response Body is like "Not Found"
     */
    @Test
    public void get02(){
        // Set the Url
        spec.pathParams("first", "booking", "second", bookingid);

        // Set the expected data
        String expectedData = "Not Found";

        // Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        assertEquals(404, response.statusCode());
        assertEquals(expectedData, response.asString());
    }
}
