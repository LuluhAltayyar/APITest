package HomeWork.day03;

import HomeWork.base_urls.BaseUrlHW09;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class HW09 extends BaseUrlHW09 {
    /*
Write an automation test that will create a 'user' using the "https://petstore.swagger.io/" document
*/

    @Test
    public void createUser(){
        spec.pathParams("first","v2",
                "second", "user");

        //Set the expected data(Payload) --> Prepare it as Map
        Map<String, Object> payLoad = new HashMap<>();


        payLoad.put("id",4896 );
        payLoad.put("username", "MahaAli");
        payLoad.put("firstName", "Maha");
        payLoad.put("lastName", "Ali");
        payLoad.put("email", "MahaAli@gmail.com");
        payLoad.put("password", "123123");
        payLoad.put("phone", "0505859585");
        payLoad.put("userStatus", "");

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().post("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        JsonPath json = response.jsonPath();
        assertEquals(200,response.statusCode());

        Map<String,Object> actualData = response.as(Map.class);
        assertEquals(200,response.statusCode());
        assertEquals(payLoad.get("message"),actualData.get("id"));

    }
}
