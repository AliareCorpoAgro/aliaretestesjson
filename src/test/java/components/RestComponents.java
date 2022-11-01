package components;

import io.restassured.RestAssured;

public class RestComponents {

    public static String RestConnection(String endpoint) {
        return RestAssured.given().when().get(endpoint).then().extract().asString();
    }
}
