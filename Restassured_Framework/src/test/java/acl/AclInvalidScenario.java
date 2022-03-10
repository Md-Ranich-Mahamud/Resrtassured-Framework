package acl;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class AclInvalidScenario {

    public static String baseURI = "URL";

    @Test
    public void createUserInAclWithInvalidHomeId(){
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
            //System.out.println(response.getBody().prettyPrint());

            String jsonString = response.asString();
            Assert.assertEquals(jsonString.contains("INVALID_HOME_ID"),true);

        int statusCode = response.getStatusCode();
        System.out.println("The status code is : " + statusCode);
        //response.getBody().prettyPrint();
        Assert.assertEquals(statusCode,400);

        JsonPath jsonPathEvaluator = response.jsonPath();
        String code = jsonPathEvaluator.get("code");
        assertThat(code, equalTo("INVALID_HOME_ID"));
    }

    @Test
    public void createUserInAclWithInvalidPartnerId(){
        String payload = "";


        Response response =
                given().
                        contentType("application/json").
                        body(payload).
                        when().
                        post(baseURI + "endPoint").
                        then().
                        assertThat().
                        statusCode(500).
                        log().
                        body().
                        extract().
                        response();


        int statusCode = response.getStatusCode();
        System.out.println("The status code is : " + statusCode);
        Assert.assertEquals(statusCode,500);

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("HERITAGE_SYSTEM_FAILURE"),true);


        JsonPath jsonPathEvaluator = response.jsonPath();
        String code = jsonPathEvaluator.get("code");
        assertThat(code, equalTo("HERITAGE_SYSTEM_FAILURE"));
    }

    @Test
    public void createUserInAclWithInvalidPayLoad(){
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

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("REQUEST_INVALID"),true);

        int statusCode = response.getStatusCode();
        System.out.println("The status code is : " + statusCode);
        Assert.assertEquals(statusCode,400);

        JsonPath jsonPathEvaluator = response.jsonPath();
        String code = jsonPathEvaluator.get("code");
        assertThat(code, equalTo("REQUEST_INVALID"));
    }
}
