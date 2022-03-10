package acl;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AclValidScenario {

    public static String baseURI = "url";
    public static String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static String lgName;
    public static String randomLoginName;


    @Test(priority = 1)
    public void createUserWithRandomLoginName(){
        randomLoginName = "loginName" + generateRandomChars(candidateChars, 3);
        String payload = "";


        Response response =
                given().
                        contentType("application/json").
                        body(payload).
                        when().
                        post(baseURI + "endPoint").
                        then().
                        assertThat().
                        statusCode(400).
                        log().
                        body().
                        extract().
                        response();
        System.out.println(response.getBody().prettyPrint());

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("CUSTOMER"),true);

        JsonPath jsonPathEvaluator = response.jsonPath();
        lgName = jsonPathEvaluator.get("loginName");
        System.out.println("loginName received from response is " + lgName);
    }

    @Test(priority = 2)
    public void ValidateCreateUserWithRandomLoginName(){
        String payload = "";


        Response response =
                given().
                        pathParam("homeId",900000).
                        pathParam("loginName",lgName).
                        when().
                        get(baseURI + "endPoint").
                        then().
                        statusCode(200).
                        extract().
                        response();


        int statusCode = response.getStatusCode();
        String statusLine = response.getStatusLine();
        response.getBody().prettyPrint();
        Assert.assertEquals(statusCode,200);


    }

    @Test(priority = 3)
    public void DeleteUserInAclWithRandomLoginName(){

        Response response =
                given().pathParam("partnerID",900000).
                        pathParam("homeId",9000001).
                        pathParam("loginName",randomLoginName).
                        when().
                        delete(baseURI + "endPoint").
                        then().
                        statusCode(204).log().
                        body().
                        extract().response();

    }

    @Test(priority = 4)
    public void ValidateCreateUserIsDeleted(){

        Response response =
                given().pathParam("partnerID",900000).
                        pathParam("homeId",9000001).
                        pathParam("loginName",randomLoginName).
                        when().
                        get(baseURI + "endPoint").
                        then().
                        statusCode(404).log().
                        body().
                        extract().response();

        int statusCode = response.getStatusCode();
        response.getBody().prettyPrint();
        Assert.assertEquals(statusCode,404);
    }

    @Test(priority = 5)
    public void DeleteUserInAclWhenUserIsNotRegistered(){

        Response response =
                given().pathParam("partnerID",900000).
                        pathParam("homeId",9000001).
                        pathParam("loginName",randomLoginName).
                        when().
                        get(baseURI + "endPoint").
                        then().
                        statusCode(500).log().
                        body().
                        extract().response();

        int statusCode = response.getStatusCode();
        System.out.println("The status code is : " + statusCode);
        Assert.assertEquals(statusCode,500);

        JsonPath jsonPathEvaluator = response.jsonPath();
        String code = jsonPathEvaluator.get("code");
        assertThat(code,equalTo("USER_NOT_REGISTERED"));

    }

    private String generateRandomChars(String candidateChars,int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i<length; i++ ){
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return sb.toString();

    }

}
