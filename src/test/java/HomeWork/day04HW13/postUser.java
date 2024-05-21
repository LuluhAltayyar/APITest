package HomeWork.day04HW13;

import HomeWork.base_urls.UserBaseUrl;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.UserPojo;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class postUser extends UserBaseUrl {

    @Test
    public void test() {

        // Set Url
        spec.pathParam("first", "user");

        // Set Expected data
        UserPojo payload = new UserPojo(58, "JonaDoe", "Jona", "Doe", "Jona@gmail.com", "14785", 505859879, 0);

        // Send request and get respnse
        Response response = given(spec).body(payload).when().post("{first}");
        response.prettyPrint();

        // Do Assertions
        UserPojo actualData = response.as(UserPojo.class);

        assertEquals(200, response.statusCode());

       /* assertEquals(payload.getId(), actualData.getId());
        assertEquals(payload.getUsername(), actualData.getUsername());
        assertEquals(payload.getFirstName(), actualData.getFirstName());
        assertEquals(payload.getLastName(), actualData.getLastName());
        assertEquals(payload.getEmail(), actualData.getEmail());
        assertEquals(payload.getPassword(), actualData.getPassword());
        assertEquals(payload.getPhone(), actualData.getPhone());
        assertEquals(payload.getUserStatus(), actualData.getUserStatus());

        */

    }
    }
