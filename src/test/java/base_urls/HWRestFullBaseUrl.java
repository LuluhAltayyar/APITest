package base_urls;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
public class HWRestFullBaseUrl {

    protected RequestSpecification spec;
    @BeforeMethod
    public void setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .build();
    }
}
