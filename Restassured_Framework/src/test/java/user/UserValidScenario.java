package user;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class UserValidScenario {


    public static String baseURL = "url";
    public static String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static String id;
    public static String randomUserName;

    @Test(priority = 1)
    public void createUserInUserService(){
        randomUserName = "username" + generateRandomChars(candidateChars, 4);

        HashMap<String,String> phoneHashMap = new HashMap<>();
        phoneHashMap.put("phoneType","Day");
        phoneHashMap.put("phoneNumber","6368765678");

        List<HashMap<String,String>> phoneNumberList = new ArrayList<>();
        phoneNumberList.add(phoneHashMap);

        HashMap<String,String>  addressHashMap = new HashMap<>();
        addressHashMap.put("AddressType","Work");
        addressHashMap.put("Postacaode","12345");
        addressHashMap.put("Country","USA");
        addressHashMap.put("AddressLine1","123 Queens Ave");
        addressHashMap.put("AddressLine2","apt #301");
        addressHashMap.put("City","Brooklyn");
        addressHashMap.put("State","NYC");

        List<HashMap<String,String>> addressList = new ArrayList<>();
        addressList.add(addressHashMap);

        HashMap<String,Object> data = new HashMap<>();
        data.put("FirstName","John");
        data.put("MiddleName","M");
        data.put("LastName","Junior");
        data.put("Email","john@gmail.com");
        data.put("Address",addressList);
        data.put("phoneNumber", phoneNumberList);
        data.put("role","CUSTOMER");
        data.put("UserName",randomUserName);
        data.put("Password","Cashed123");

        Response response =
                given().
                        pathParam("PartnerId","900000").
                        pathParam("homeId","9000001").
                        contentType("application/json").
                        body(data).
                        when().
                        post(baseURL + "endpoint").
                        then().statusCode(200).log().
                        body().extract().response();

        System.out.println(response.getBody().prettyPrint());

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("patnerHomeId") , true);

        JsonPath jsonPathEvaluator = response.jsonPath();
        id = jsonPathEvaluator.get("id");
        System.out.println("id from response is " +id);
        String state = jsonPathEvaluator.get("state");
        assertThat(state, equalTo("ACTIVE"));
    }

    @Test(priority = 2)
    public void ValidCreateUserInUserService(){

       Response response =
                given().
                pathParam("userId",id).
                when().
                get(baseURL + "endpoint").
                then().statusCode(200).
                extract().response();

        int statusCode = response.getStatusCode();
        response.getBody().prettyPrint();
        Assert.assertEquals(statusCode, 200);

        JsonPath jsonPathEvaluator = response.jsonPath();
        String role = jsonPathEvaluator.get("role");
        assertThat(role, equalTo("CUSTOMER"));

    }

    @Test(priority = 3)
    public void getUserInUserServiceWithPtnerUserNameAndHomeId(){

        Response response =
                given().
                        pathParam("PartnerUserName", randomUserName).
                        pathParam("homeId","9000001").
                        when().
                        get(baseURL + "endpoint").
                        then().statusCode(200).
                        extract().response();

        int statusCode = response.getStatusCode();
        response.getBody().prettyPrint();
        Assert.assertEquals(statusCode, 200);
    }

    @Test(enabled = false , priority = 4)
    public void deleteUserInUserService() {

        Response response =
                given().
                        pathParam("PartnerId", "900000").
                        pathParam("homeId", "9000001").
                        pathParam("loginName",randomUserName).
                        when().
                        delete(baseURL + "endpoint").
                        then().statusCode(204).log().
                        body().extract().response();


    }

    @Test(enabled = false , priority = 5)
    public void ValidateCreateUserIsDeletedInUserService() {


        Response response =
                given().
                        pathParam("PartnerId", "900000").
                        pathParam("homeId", "9000001").
                        when().
                        get(baseURL + "endpoint").
                        then().statusCode(404).
                        extract().response();

        int statusCode = response.getStatusCode();
        response.getBody().prettyPrint();
        Assert.assertEquals(statusCode, 404);
    }

    private String generateRandomChars(String candidateChars, int length){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0; i<length; i++){
            sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
        }
        return sb.toString();
    }

}
