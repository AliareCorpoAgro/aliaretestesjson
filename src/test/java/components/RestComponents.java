package components;

import org.json.JSONObject;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class RestComponents {

    public static String RestConnection(String endpoint) {
        return given().when().get(endpoint).then().extract().asString();
    }

    public static void iFNull(JSONObject response, String pathNull){
        if (response.get(pathNull).getClass().getSimpleName().equals("Null")){
            throw new RuntimeException("O campo está Null:\n" + pathNull);
        }
    }
    public static String Inicialization(String endpoint){
        return given().when().get(endpoint).then().extract().asString();
    }

}
