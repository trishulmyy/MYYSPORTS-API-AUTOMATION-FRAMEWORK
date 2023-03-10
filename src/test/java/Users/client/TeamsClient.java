package Users.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import util.FileUtility;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TeamsClient {

    public   String propertyPath = System.getProperty("user.dir") + "//src//main//java//spec.properties";
    public   Properties properties;

    public  Response getTeam(String id) {

        properties= FileUtility.loadProperties(propertyPath);
        String bearerToken=properties.getProperty("bearerToken");


        Response response = given()
                .header("Authorization","Bearer "+bearerToken)
                .pathParam("TeamId",id)
                .log().uri()
                .when()
                .get(properties.getProperty("basepath")+"/{TeamId}");
        response
                .then()
                .contentType(ContentType.JSON)
                .log().body();
        return response;

    }

    public  Response getTeams() {

        properties= FileUtility.loadProperties(propertyPath);
        String bearerToken=properties.getProperty("bearerToken");


        Response response = given()
                .header("Authorization","Bearer "+bearerToken)
                .queryParam("page",1)
                .log().uri()
                .when()
                .get("https://dev-scoring.platform.myysports.com/api/team");
        response
                .then()
                .contentType(ContentType.JSON)
                .log().body();
        return response;

    }


}
