package components;

import org.json.JSONArray;
import org.json.JSONObject;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class RestComponents {

    public static String RestConnection(String endpoint) {
        return given().when().get(endpoint).then().extract().asString();
    }

    public static void iFNull(JSONObject response, String pathNull) {
        if (response.has(pathNull) && response.get(pathNull).getClass().getSimpleName().equals("Null")) {
//            throw new RuntimeException("O campo está Null:\n" + pathNull + " id: " + response.get("id"));
            if (response.has("id")) {
                throw new RuntimeException("O campo está Null:\n" + pathNull + ", Do id: " + response.get("id"));
            } else if (response.has("nome")) {
                throw new RuntimeException("O campo está Null:\n" + pathNull + ", Do cliente: " + response.get("nome"));
            } else throw new RuntimeException("O campo está Null:\n" + pathNull);

        }
    }
}
