package HomeWork.day03;

import HomeWork.base_urls.BaseUrlHW11;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class HW11 extends BaseUrlHW11 {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends a GET request
    Then
        Assert that the number of "Women" user type is 12
    Note: Print using JsonPath: response.jsonPath().prettyPrint();
*/
    @Test
    public void Test(){

        spec.pathParams("first","api",
                "second", "productsList");

        Response response = given(spec).when().get("{first}/{second}");

        // Print response body using JsonPath
        response.jsonPath().prettyPrint();


        // Assert that the number of "Women" user type is 12
        List<String> userTypeList = response.jsonPath().getList("products.findAll { it.category.usertype.usertype == 'Women' }");
        int numberOfWomenUserType = userTypeList.size();
        System.out.println(numberOfWomenUserType );
        assertEquals(numberOfWomenUserType, 12, "Number of 'Women' user type is not 12");
    }

}
