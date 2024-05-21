package HomeWork.day03;

import HomeWork.base_urls.BaseUrlHW12;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojos.JsonPlaceHolderPojoHW12;

import java.util.Collections;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
public class HW12 extends BaseUrlHW12 {

    ////Write an automation test that will create, read, update, delete a 'pet' using the "https://petstore.swagger.io/" document
    //(All actions must be done on same pet)
    //(Use Pojo)
    int id= 78;
    String name ="doggie";
    String status ="available";

    @Test
    public void post() {
        // Set Url
        spec.pathParam("first", "pet");
        // Create a new pet
        JsonPlaceHolderPojoHW12 newPet = new JsonPlaceHolderPojoHW12(id, name,status );

        Response createResponse = given(spec)
                .body(newPet)
                .when()
                .post("/{first}");
        createResponse.prettyPrint();


        JsonPlaceHolderPojoHW12 createdPet = createResponse.as(JsonPlaceHolderPojoHW12.class);

        // Validate the created pet
        assertEquals(200, createResponse.statusCode());
        assertEquals(createdPet.getId(), newPet.getId());
        assertEquals(createdPet.getName(), newPet.getName());
        assertEquals(createdPet.getStatus(), newPet.getStatus());

    }

    @Test(priority = 2)
    public void get() {
        // Set Url
        spec.pathParams("first","pet",
                "second", id);


        // Read the pet
        Response getResponse = given(spec)
                .when()
                .get("/{first}/{second}");


        getResponse.prettyPrint();
        JsonPlaceHolderPojoHW12 createdPet = getResponse.as(JsonPlaceHolderPojoHW12.class);

        // Validate the created pet
        assertEquals(200, getResponse.statusCode());
        assertEquals(createdPet.getName(), name);
        assertEquals(createdPet.getStatus(), status);

    }

    @Test(priority = 3)
    public void put() {
       String updateStatus="sold";
        // Set Url
        spec.pathParams("first","pet");
        JsonPlaceHolderPojoHW12 updatedPet = new JsonPlaceHolderPojoHW12(id, name, updateStatus);

        // update the pet
        Response response = given(spec)
                .body(updatedPet)
                .when()
                .put("/{first}");


        response.prettyPrint();
        JsonPlaceHolderPojoHW12 updatePet = response.as(JsonPlaceHolderPojoHW12.class);

        // Validate the created pet
        assertEquals(updatePet.getStatus(), updateStatus);

    }
    @Test(priority = 4)
    public void delete() {
        // Set Url
        spec.pathParams("first","pet","second", id);

        // update the pet
        Response response = given(spec)
                .when()
                .delete("/{first}/{second}");

        // Validate the created pet
        assertEquals(200,response.statusCode() );

    }
}
