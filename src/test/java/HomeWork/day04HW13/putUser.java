package HomeWork.day04HW13;

import HomeWork.base_urls.UserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class putUser  extends UserBaseUrl {

    @Test(dependsOnMethods = "HomeWork.day04.postUser.test")
    public void test() {
        String username = "JonaDoe";

        // Set Url
        spec.pathParams("first","user","second", username);

        // Set Expected data
        UserPojo payload = new UserPojo(58, "JonaDoe", "Jona", "Doe", "Jona1@gmail.com", "14785", 505859879, 0);

        // Send request and get respnse
        Response response = given(spec).body(payload).when().put("{first}/{second}");
        response.prettyPrint();

        assertEquals(200, response.statusCode());

        // Do Assertions

        assertEquals(response.jsonPath().getInt("message"), 58);
        }
    }
