package HomeWork.day04HW13;

import HomeWork.base_urls.UserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class getUser extends UserBaseUrl {

    @Test(dependsOnMethods = "HomeWork.day04.postUser.test")
    public void test() {
        String username = "JonaDoe";

        // Set Url
        spec.pathParams("first","user","second", username);

        // Set Expected data
        // UserPojo payload = new UserPojo(58, username, "Jona", "Doe", "Jona@gmail.com", "14785", 505859879, 0);

        // Send request and get respnse
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        assertEquals(200, response.statusCode());

        // Do Assertions
        UserPojo actualData = response.as(UserPojo.class);


        assertEquals(58, actualData.getId());
        assertEquals("JonaDoe", actualData.getUsername());
        assertEquals("Jona", actualData.getFirstName());
       /* assertEquals(response.jsonPath().getString("lastName"), actualData.getLastName());
        assertEquals(response.jsonPath().getString("email"), actualData.getEmail());
        assertEquals(response.jsonPath().getString("password"), actualData.getPassword());
        assertEquals(response.jsonPath().getInt("phone"), actualData.getPhone());
        assertEquals(response.jsonPath().getInt("userStatus"), actualData.getUserStatus());
*/


    }
}
