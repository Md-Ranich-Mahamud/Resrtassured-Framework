package aclservice;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ACLServicePost {

    String baseURI = "URL";

    @Test(enabled = false)
    public void create_User_Acl(){

        String payload = "";


        Response response =
                given().
                        contentType("application/json").
                        body(payload).when().post(baseURI + "endPoint").
                        then().
                        statusCode(200).
                        log().
                        body().
                        extract().
                        response();
        System.out.println(response.getBody().prettyPrint());

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("CUSTOMER"),true);
    }

    @Test(enabled = false)
    public void validate_Create_User_Acl(){
        Response response =
                given().
                        pathParam("homeId",900000).
                        pathParam("logingName","").
                        get(baseURI + "endpoint").
                        then().
                        statusCode(200).
                        extract().
                        response();
        int statusCode = response.getStatusCode();
        response.getBody().prettyPrint();
        Assert.assertEquals(statusCode,200);
    }
}
