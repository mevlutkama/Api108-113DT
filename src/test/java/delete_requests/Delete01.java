package delete_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Delete01 extends JsonPlaceHolderBaseUrl {
   /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01(){

        // Set the Url
        spec.pathParams("first", "todos", "second", 198);

        // Set the expected data
        Map<String,Object> expectedData = new HashMap<>();
        System.out.println("expectedData = " + expectedData);

        // Send the Request and get the Result
        Response response = given().spec(spec).when().delete("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData, actualData);

        // or
        assertEquals(expectedData.size(), actualData.size());

        // or
        assertTrue(actualData.isEmpty());

        // or
        response.then().assertThat(). body("isEmpty()", Matchers.is(true));

         /*
           How to automate Delete Request in API Testing?
           i) Create a new data by using "Post Request"
           ii) Use "Delete Request" to delete new data.

           Note: Do not delete existing data, create a data to delete.
         */
    }
}
