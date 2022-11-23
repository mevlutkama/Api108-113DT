package get_requests;

import base_urls.RegresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14 extends RegresBaseUrl {
    /*
       Given
           https://reqres.in/api/users/23
       When
           User send a GET Request to the url
       Then
           HTTP Status code should be 404
       And
           Status Line should be HTTP/1.1 404 Not Found
       And
           Server is "cloudflare"
       And
           Response body should be empty

    */

    @Test
    public void get14(){

        // Set the Url
        spec.pathParams("first","users","second",23);

        // Set the expected data

        // Send the Request and get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        assertEquals(404,response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found", response.statusLine());
        assertEquals("cloudflare", response.getHeader("Server"));
        assertEquals(0, response.asString().replaceAll("[^A-Za-z0-9]","").length());
        assertEquals(0,response.as(HashMap.class).size());
    }
}
