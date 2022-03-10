package aclservice;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ACLServiceDelete {

    String BASE_URI = "URL";

    @Test(enabled = false)
    public void DELETE_USER_ACL(){


        Response res = given().pathParam("patnerId","900000").
                pathParam("homeId","900001").
                pathParam("loginName","Tree").
                when().
                delete(BASE_URI+"END POINT").
                then().
                statusCode(204).
                log().body().
                extract().
                response();

        String jsonAsString = res.asString();
    }
}
