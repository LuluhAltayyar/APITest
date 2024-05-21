package HomeWork.day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.*;

public class HW4 {
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
    void Test() {
//        https://reqres.in/api/users/23
        String url = " https://reqres.in/api/users/23";

        //        User sends a GET Request to the URL
        Response response = given().when().get(url);
        response.prettyPrint();

        response
                .then()
                .statusCode(404)
                .statusLine("HTTP/1.1 404 Not Found")
                .header("Server", "cloudflare")
                .body("isEmpty()", equalTo(true)); // Ensure that the response body is empty
        // .body(equalTo("{}"));
    }

}
