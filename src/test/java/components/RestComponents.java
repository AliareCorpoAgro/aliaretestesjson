package components;

import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class RestComponents {

    public static String RestConnection(String endpoint) {
        return given().when().get(endpoint).then().extract().asString();
    }

    public static String PathGet(String string){
        return given().when().get().then().extract().path(string);
    }

}
