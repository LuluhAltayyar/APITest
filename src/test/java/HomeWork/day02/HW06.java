package HomeWork.day02;

import base_urls.HWRestFullBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;

public class HW06 extends HWRestFullBaseUrl {
     /*
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json; charset=utf-8"
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */

    @Test
    public void test() {
        spec.pathParams("first", "api",
                "second", "unknown",
                "third", 3);
        Response response = given(spec).when().get("{first}/{second}/{third}");
        response.prettyPrint();

        //1st way: then() method with hamcrest matchers
        response
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON.withCharset("utf-8"));

        //2nd way: By extracting data outside the body with JSONPath
        //Convert Response to JsonPath object
        JsonPath json = response.jsonPath();

        //Retrieve the desired data by using JsonPath object
        int id = json.getInt("data.id");
        String name = json.getString("data.name");
        int year = json.getInt("data.year");
        String color = json.getString("data.color");
        String pantone_value = json.getString("data.pantone_value");
        String text = json.getString("support.text");



        //use Soft Assertion
        //1st step: Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();
        //2nd step: Do assertion
        softAssert.assertEquals(3, id); //If this assertion fails, the subsequent lines will execute as well. Because this is Soft Assertion.
        softAssert.assertEquals("true red", name);
        softAssert.assertEquals(2002, year);
        softAssert.assertEquals("#BF1932", color);
        softAssert.assertEquals("19-1664", pantone_value);
        softAssert.assertEquals("To keep ReqRes free, contributions towards server costs are appreciated!", text);

        //3rd step: Use assertAll() method.
        softAssert.assertAll();


    }

    public static class HW07 extends HWRestFullBaseUrl {
         /*
           Given
                  https://reqres.in/api/unknown/
           When
                I send GET Request to the URL
           Then
                1)Status code is 200
                2)Print all pantone_values
                3)Print all ids greater than 3 on the console
                  Assert that there are 3 ids greater than 3
                4)Print all names whose ids are less than 3 on the console
                  Assert that the number of names whose ids are less than 3 is 2
        */
         @Test
         public void test() {
             spec.pathParams("first", "api",
                     "second", "unknown");
             Response response = given(spec).when().get("{first}/{second}/");
             response.prettyPrint();
            // 1)Status code is 200
             response.then().statusCode(200);
             //2)Print all pantone_values
             JsonPath json = response.jsonPath();
             List<String> pantoneList = json.getList("data.pantone_value");

             System.out.println("pantoneList = " + pantoneList);

            // 3)Print all ids greater than 3 on the console
             //Assert that there are 3 ids greater than 3
             List<Integer> idList = json.getList("data.id");
             int idGreaterThan3 = 0;


             for (int i = 1; i <= idList.size(); i++)
                 if (i>3){
                     System.out.println(i);
                     idGreaterThan3++;
                 }

             Assert.assertEquals(idGreaterThan3,3);

            // 4)Print all names whose ids are less than 3 on the console
             //Assert that the number of names whose ids are less than 3 is 2

             List<String> nameList = json.getList("data.findAll{it.id<3}.name");
             System.out.println("nameList "+ nameList);
            // Assert.assertEquals(nameList,2);
             assertThat("Number of names whose ids are less than 3", nameList.size(), equalTo(2));




         }
    }
}
