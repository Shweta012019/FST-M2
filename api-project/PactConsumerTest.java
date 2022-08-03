package LiveProject;

import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@ExtendWith(PactConsumerTestExt.class)
public class PactConsumerTest {
    //Create hash map
    Map<String,String> headers=new HashMap<String,String>();
   // Set Resource URI
   String API_User="/api/users";
   @Pact(consumer="UserConsumer",provider="UserProvider")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
       //set headers
       headers.put("Content-Type", "application/json");

       DslPart RequestResponseBody = new PactDslJsonBody()
               .numberType("id")
               .stringType("firstName")
               .stringType("lastName")
               .stringType("email");


       return builder.given("A request to create a user")
               .uponReceiving("A request to create a user")
               .method("POST")
               .headers(headers)
               .path(API_User)
               .body(RequestResponseBody)
               .willRespondWith()
               .status(201)
               .body(RequestResponseBody)
               .toPact();
   }
   @Test
   @PactTestFor(providerName = "UserProvider",port="8080")
   public void ConsumerTest(){
     final String baseURI="http://localhost:8080";
      Map<String,Object> reqBody=new HashMap<String,Object>();
       reqBody.put("id",99);
       reqBody.put("firstName","Justin");
       reqBody.put("lastName","Case");
       reqBody.put("email","JustinCase@mail.com");
       Response response=given().headers(headers).when().body(reqBody).post(baseURI+API_User);
       response.then().statusCode(201);
   }
}
