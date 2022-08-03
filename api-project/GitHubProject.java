package LiveProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

public class GitHubProject {
    RequestSpecification reqSpec;
   String BaseUri="https://api.github.com/user/keys";
    int id;

  @BeforeClass
    public void Set_Up(){
      String TokenId="token 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";
      reqSpec=new RequestSpecBuilder()
              .addHeader("Authorization",TokenId)
              .setAuth(oauth2("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"))
              .setBaseUri(BaseUri)
              .setContentType(ContentType.JSON)
              .build();
        }
     @Test(priority=1)
    public void post_Ssh_Key(){
         String ssh_Key="ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCIfWimqAWZp15ZEpf8cOjdcgS/ThXD2KPwFS6U32h36Fj29LpeqUHmmHPBuGKRJuKT04fyZXiWhMXFA5ggPaSEbLO5UHlnPk+trE85ZzjwCGfm38GykxLI9B7vZH89Qrdxae4253r0ClWW3QYl9vFMA47YJywB70OW67jLaU4Zg1PNWAliJdy3vKvKtbXHlWTQX8E3tFF+xpOTPs6IJ57ZAiCGUDu0fi3uUpE0gUd4jerKHUhjKpYDW8jKtjWDGx7RW++MqokplInDmFCRer76gDevTbZtiYKc24AOuNZt0TcoeyUhtDeojlcmBXTidbt4buuVWR9YEsUH58ur+I7R";
         String reqBody= "{\"title\":\"TestAPIKey\",\"key\":\""+ssh_Key+"\"}";
         Response response=given().spec(reqSpec).body(reqBody).when().post();
                 System.out.println(response.asPrettyString());
                  id=response.then().extract().path("id");
         response.then().statusCode(201);
     }
    @Test(priority=2)
    public void get_Ssh_Key(){
        given().log().all().spec(reqSpec).pathParam("keyid", id).when().get("/{keyid}").then().log().body().statusCode(200);
    }
    @Test(priority=3)
    public void delete_Ssh_Key(){
        given().log().all().spec(reqSpec).pathParam("keyid", id).when().delete("/{keyid}").then().log().body().statusCode(204);
    }
}
