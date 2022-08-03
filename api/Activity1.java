package Activities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.*;
import java.io.UnsupportedEncodingException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToCompressingWhiteSpace;

public class Activity1 {
    final String postUrl="https://petstore.swagger.io/v2/pet";
    final String getRequest="https://petstore.swagger.io/v2/pet/{petId}";
    final String deleteRequest="https://petstore.swagger.io/v2/pet/{petId}";
    String reqBody = "{\"id\": 77232,\"name\": \"Riley\",\"status\": \"alive\"}";
    @Test(priority = 1)
    public void postRequest() throws IOException {
        Response response=given().contentType(ContentType.JSON).
                body(reqBody).
                when().post(postUrl);
        response.then().body("id",equalTo(77232));
        response.then().body("name",equalTo("Riley"));
        response.then().body("status",equalTo("alive"));
    }
    @Test(priority = 2)
    public void getRequest(){
        Response response=given().contentType(ContentType.JSON).
                pathParam("petId","77232").
                when().get(getRequest);
        response.then().body("id", equalTo(77232));
        response.then().body("name", equalTo("Riley"));
        response.then().body("status", equalTo("alive"));
    }
    @Test(priority = 3)
    public void DeleteRequest(){
     Response response=given().contentType(ContentType.JSON).
             pathParam("petId","77232").
             when().delete(deleteRequest);
        response.then().body("code", equalTo(200));
        response.then().body("message", equalTo("77232"));
    }
}
