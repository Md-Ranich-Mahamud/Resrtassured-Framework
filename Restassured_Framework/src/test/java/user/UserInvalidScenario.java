package user;


import io.restassured.response.Response;
import junit.framework.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import static org.hamcrest.CoreMatchers.equalTo;

import static io.restassured.RestAssured.given;


public class UserInvalidScenario {

    public static String baseURL = "url";
    public static String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static String id;
    public static String randomUserName;

    @Test(priority = 1)
    public void create_user(){
        randomUserName = "username" + generateRandomChars(candidateChars, 3);

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
        System.out.println("UserName is : " +randomUserName);

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("id") , true);
    }

    @Test(priority = 2)
    public void createUserWhenUserIsAlreadyRegistered(){

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

        given().
                pathParam("PartnerId","900000").
                pathParam("homeId","9000001").
                contentType("application/json").
                body(data).
                when().
                post(baseURL + "endpoint").
                then().statusCode(400).
                log().body().
                log().body().body("body", equalTo("USER_ALREADY+REGISTERED"));


    }

    @Test(priority = 3)
    public void createUserWithInvalidHomeId(){

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
                        then().assertThat().statusCode(500).log().
                        body().extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("SUBSYSTEM_FAILURE") , true);

        int statusCode = response.getStatusCode();
        System.out.println("The status code is : " + statusCode);
        Assert.assertEquals(statusCode, 500);
    }

    @Test(priority = 4)
    public void createUserWithInvalidPatnerId() {

        HashMap<String, String> phoneHashMap = new HashMap<>();
        phoneHashMap.put("phoneType", "Day");
        phoneHashMap.put("phoneNumber", "6368765678");

        List<HashMap<String, String>> phoneNumberList = new ArrayList<>();
        phoneNumberList.add(phoneHashMap);

        HashMap<String, String> addressHashMap = new HashMap<>();
        addressHashMap.put("AddressType", "Work");
        addressHashMap.put("Postacaode", "12345");
        addressHashMap.put("Country", "USA");
        addressHashMap.put("AddressLine1", "123 Queens Ave");
        addressHashMap.put("AddressLine2", "apt #301");
        addressHashMap.put("City", "Brooklyn");
        addressHashMap.put("State", "NYC");

        List<HashMap<String, String>> addressList = new ArrayList<>();
        addressList.add(addressHashMap);

        HashMap<String, Object> data = new HashMap<>();
        data.put("FirstName", "John");
        data.put("MiddleName", "M");
        data.put("LastName", "Junior");
        data.put("Email", "john@gmail.com");
        data.put("Address", addressList);
        data.put("phoneNumber", phoneNumberList);
        data.put("role", "CUSTOMER");
        data.put("UserName", randomUserName);
        data.put("Password", "Cashed123");

        Response response =
                given().
                        pathParam("PartnerId", "900000").
                        pathParam("homeId", "9000001").
                        contentType("application/json").
                        body(data).
                        when().
                        post(baseURL + "endpoint").
                        then().statusCode(500).log().
                        body().extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("SUBSYSTEM_FAILURE") , true);

        int statusCode = response.getStatusCode();
        System.out.println("The status code is : " + statusCode);
        Assert.assertEquals(statusCode, 500);
    }

    @Test(priority = 5)
    public void createUserWithInvalidPayload() {

        HashMap<String, String> phoneHashMap = new HashMap<>();
        phoneHashMap.put("phoneType", "Day");
        phoneHashMap.put("phoneNumber", "6368765678");

        List<HashMap<String, String>> phoneNumberList = new ArrayList<>();
        phoneNumberList.add(phoneHashMap);

        HashMap<String, String> addressHashMap = new HashMap<>();
        addressHashMap.put("AddressType", "Work");
        addressHashMap.put("Postacaode", "12345");
        addressHashMap.put("Country", "USA");
        addressHashMap.put("AddressLine1", "123 Queens Ave");
        addressHashMap.put("AddressLine2", "apt #301");
        addressHashMap.put("City", "Brooklyn");
        addressHashMap.put("State", "NYC");

        List<HashMap<String, String>> addressList = new ArrayList<>();
        addressList.add(addressHashMap);

        HashMap<String, Object> data = new HashMap<>();
        data.put("FirstName", "John");
        data.put("MiddleName", "M");
        data.put("LastName", "Junior");
        data.put("Email", "john@gmail.com");
        data.put("Address", addressList);
        data.put("phoneNumber", phoneNumberList);
        data.put("role", "CUSTOMER");
        data.put("UserName", randomUserName);
        data.put("Password", "Cashed123");

        Response response =
                given().
                        pathParam("PartnerId", "900000").
                        pathParam("homeId", "9000001").
                        contentType("application/json").
                        body(data).
                        when().
                        post(baseURL + "endpoint").
                        then().statusCode(400).log().
                        body().extract().response();

        String jsonString = response.asString();
        Assert.assertEquals(jsonString.contains("REQUEST_INVALID") , true);

        int statusCode = response.getStatusCode();
        System.out.println("The status code is : " + statusCode);
        Assert.assertEquals(statusCode, 400);
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