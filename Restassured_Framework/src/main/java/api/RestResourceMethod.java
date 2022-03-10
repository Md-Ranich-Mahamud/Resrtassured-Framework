package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;


import static api.SpeceBuilder.getRequestSpec;
import static api.SpeceBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class RestResourceMethod {


    public static Response post(String path, Object requestUserServiceList){

        return RestAssured.given(getRequestSpec()).
                body(requestUserServiceList).
                when().
                post(path).
                then().
                spec(getResponseSpec()).
                extract().
                response();


    }

    public static Response get(String path){

        return given(getRequestSpec()).
                when().
                post(path).
                then().spec(getResponseSpec()).

                extract().
                response();


    }

    public static Response update(String path, Object requestUserServiceList) {

        return given(getRequestSpec()).
                body(requestUserServiceList).
                when().
                put().
                then().
                spec(getResponseSpec()).
                extract().
                response();


    }

    public static Response postAccount(String path, Object requestUserServiceList) {

        return given().
                when().
                post(path).
                then().
                statusCode(201).
                extract().
                response();
    }
}