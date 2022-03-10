package acl;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestAclUser {

    String baseURI = "URL";

    @Test(enabled = false)
    public void create_User_Acl() {

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
        Assert.assertEquals(jsonString.contains("CUSTOMER"), true);
    }

    @Test(enabled = false)
    public void ValidateCreateUser_acl() {
        String payload = "";


        Response response =
                given().
                        pathParam("homeId", 900000).
                        pathParam("loginName", "").
                        get(baseURI + "endPoint").
                        then().
                        statusCode(200).
                        extract().
                        response();


        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();
        response.getBody().prettyPrint();
        Assert.assertEquals(statusCode, 200);
    }

    @Test(enabled = false)
    public void Validate_Delete_User_Acl() {

        Response response =
                given().pathParam("partnerID", 900000).
                        pathParam("homeId", 9000001).
                        pathParam("loginName", "").
                        when().
                        delete(baseURI + "endPoint").
                        then().
                        statusCode(204).log().
                        body().
                        extract().response();
        String jsonAsString = response.asString();
    }
}