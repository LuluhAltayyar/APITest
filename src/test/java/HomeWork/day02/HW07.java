package HomeWork.day02;

import base_urls.HWRestFullBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class HW07 extends HWRestFullBaseUrl {
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