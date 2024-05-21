package HomeWork.day03;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class HW10 {

   // Using the https://petstore.swagger.io/ document, write an automation test that finds the number of "pets" with the status "available" and asserts that there are more than 100.

    @Test
    public void pets(){
        String url= "https://petstore.swagger.io/v2/pet/findByStatus?status=available";
        Response response = given().when().get(url);
       // response.prettyPrint();

        // Extract the number of available pets
        int numberOfAvailablePets = response.jsonPath().getList("$").size();

        System.out.println(numberOfAvailablePets);
        // Assert that there are more than 100 available pets
        assertTrue(numberOfAvailablePets > 100);


    }

}
