package get_requests;

import base_urls.PetStoreApiBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.List;
import static io.restassured.RestAssured.given;

public class GetPetStore extends PetStoreApiBaseUrl {


    /*
        Given
            User goes to https://petstore.swagger.io/v2/pet/12
        When
            User sends to GET request
        Then
            HTTP status code 200
        And
            Verify response body
     */


    @Test
    public void get01() {

        spec.pathParams("first", "pet", "second", "findByStatus").queryParams("status", "available");

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        JsonPath json = response.jsonPath();
        List<Long> idList = json.getList("findAll{it.id}.id");
        System.out.println("idList = " + idList);

        // Get the photoUrls from the pet with name ‘80e2c334-96bf-4522-ac66-a4079d73d8b0’
        String nameList = json.getString("name");
        System.out.println("nameList = " + nameList);
    }
}

