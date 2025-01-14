package HomeWork.base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;

public class BaseUrlHW08 {

    protected RequestSpecification spec;

    @BeforeMethod
    public void setUp(){

        spec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in").setContentType(ContentType.JSON)
                .build();

    }

}
