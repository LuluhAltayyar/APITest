package HomeWork.base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseUrlHW12 {
    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp(){

        spec = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2").setContentType(ContentType.JSON)
                .build();

    }
}
