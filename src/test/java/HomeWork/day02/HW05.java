package HomeWork.day02;

import base_urls.HWRestFullBaseUrl;
import base_urls.RestFullBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class HW05 extends HWRestFullBaseUrl {
    /*
       Given
           https://reqres.in/api/users/2
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be "application/json"
       And
           "email" is "janet.weaver@reqres.in",
       And
           "first_name" is "Janet"
       And
           "last_name" is "Weaver"
       And
           "text" is "To keep ReqRes free, contributions towards server costs are appreciated!"
    */

    @Test
    public void test()
    {
        spec.pathParams("first","api",
                "second","users",
                "third",2);
        Response response= given(spec).when().get("{first}/{second}/{third}");
        response.prettyPrint();

        //1st way: then() method with hamcrest matchers
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data.email",equalTo("janet.weaver@reqres.in"))
                .body("data.first_name",equalTo("Janet"))
                .body("data.last_name",equalTo("Weaver"))
                .body("support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!"));


        //2nd way: By extracting data outside the body with JSONPath
        //Convert Response to JsonPath object
        JsonPath json =response.jsonPath();

        //Retrieve the desired data by using JsonPath object
        String firstname = json.getString("data.first_name");
        String lastname = json.getString("data.last_name");
        String email = json.getString("data.email");
        String text = json.getString("support.text");

        assertEquals("Janet",firstname);//If this assertion fails, the subsequent lines won't execute. Because this is Hard Assertion.
        assertEquals("Weaver",lastname);
        assertEquals("janet.weaver@reqres.in",email);
        assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",text);

        //use Soft Assertion
        //1st step: Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();
        //2nd step: Do assertion
        softAssert.assertEquals("Janet",firstname); //If this assertion fails, the subsequent lines will execute as well. Because this is Soft Assertion.
        softAssert.assertEquals("Weaver",lastname);
        softAssert.assertEquals("janet.weaver@reqres.in",email);
        softAssert.assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!",text);


        //3rd step: Use assertAll() method.
        softAssert.assertAll();



    }
}
