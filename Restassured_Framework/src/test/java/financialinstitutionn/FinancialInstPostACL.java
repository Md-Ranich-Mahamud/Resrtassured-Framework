package financialinstitutionn;
import static io.restassured.RestAssured.baseURI;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class FinancialInstPostACL {

    public void financialInstByFIList(){
        JSONObject request = new JSONObject();
        request.put("partnerId","900000");
        request.put("homeId","900001");
        request.put("financialId","900002");
        request.put("financialId","900003");
        request.put("financialId","900004");
        request.put("financialId","900005");

        baseURI = "URL";
        Response response =
                given().
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        header("Content-Type","application/json").
                        body(request.toJSONString()).
                        when().post("url").
                        then().
                        statusCode(201).
                        log().
                        body().
                        extract().
                        response();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,201);
        String successCode = response.jsonPath().get("SuccessCode");


    }

    public void allFinancialInstitution(){

        JSONObject request = new JSONObject();
        request.put("partnerId","900000");
        request.put("homeId","900001");


        baseURI = "URL";
        Response response =
                given().
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        header("Content-Type","application/json").
                        body(request.toJSONString()).
                        when().post("url").
                        then().
                        statusCode(201).
                        log().
                        body().
                        extract().
                        response();

        int statusCode = response.getStatusCode();
        System.out.println(statusCode);
        Assert.assertEquals(statusCode,201);
        String successCode = response.jsonPath().get("SuccessCode");


    }
}
