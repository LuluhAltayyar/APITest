package HomeWork.day04HW13;

import HomeWork.base_urls.UserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class deleteUser extends UserBaseUrl {

    @Test(dependsOnMethods = "HomeWork.day04.postUser.test")
    public void test() {
        String username = "JonaDoe";

        // Set Url
        spec.pathParams("first", "user", "second", username);

        // Send request and get respnse
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        assertEquals(200, response.statusCode());

        // Do Assertions

        assertEquals("JonaDoe",response.jsonPath().getString("message"));
    }
}
