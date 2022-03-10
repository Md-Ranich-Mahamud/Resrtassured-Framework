package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import static api.Route.BASE_PATH;

public class SpeceBuilder {


    public static RequestSpecification getRequestSpec(){

        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("BASE_URI")).
                setBasePath(BASE_PATH).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();

    }

    public static RequestSpecification getAccountRequestSpec(){

        return new RequestSpecBuilder().
                setBaseUri(System.getProperty("ACCOUNT_BASE_URI")).
                setContentType(ContentType.JSON).
                log(LogDetail.ALL).
                build();


    }
    public static ResponseSpecification getResponseSpec() {

        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();


    }
}