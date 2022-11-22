package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

            /*
                1) Postman is used for manual API testing.
                2) We use Rest - Assured Library for Automation API testing.
                3) To type automation script follow the steps:
                    a) Understand the requirement.
                    b) Type the test cases.
                        To type cases we use "Gherkin Language"
                        The keywords are x) Given: It is used for preconditions
                                         y) When: It is used for actions
                                         z) Then: It is used for outputs
                                         t) And: It is used for multiple given, when and then
                    c) Start to type Automation Script
                        i) Set the Url
                        ii) Set the expected data(Post, Put, Patch requests)
                        iii) Send the Request and get the Response
                        iv) Do Assertion
             */

             /*
                Given
                    https://restful-booker.herokuapp.com/booking/101
                When
                    User sends a GET Request to the url
                Then
                    HTTP Status Code should be 200
                And
                    Content Type should be application/json
                And
                    Status Line should be HTTP/1.1 200 OK
             */

    @Test
    public void get01() {
        // Set the Url
        String url = "https://restful-booker.herokuapp.com/booking/101";

        // Set the expected data

        // Send the Request and get the Response
        Response response = given().when().get(url);
        response.prettyPrint();

        // Do Assertion
        response.then().assertThat().statusCode(200).
                contentType("application/json").statusLine("HTTP/1.1 200 OK");

        // How to print "status code" on the console
        System.out.println("Status Code: " + response.statusCode());

        // How to print "content type" on the console
        System.out.println("Content Type: " + response.contentType());

        // How to print "status line" on the console
        System.out.println("Status Line: " + response.statusLine());

        // How to print "Header" on the console
        System.out.println(response.getHeader("Server"));
        System.out.println(response.getHeader("Connection"));

        System.out.println("==========================");

        // How to print "Headers" on the console
        System.out.println(response.getHeaders());

        // How to print "Time" on the console
        System.out.println(response.getTime());
    }
}
