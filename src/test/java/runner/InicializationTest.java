package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"features"},
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        glue = {"step"},
        publish = true
)
public class InicializationTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://demo3675470.mockable.io/";
        String body = given().when().get().then().extract().response().asString();

        JSONObject jsonObject = new JSONObject(body);
        JSONArray jsonArray;
        jsonArray = jsonObject.getJSONArray("enterprises_buy");

        JSONObject jsonObjectBaseData = new JSONObject(body);
        jsonObjectBaseData = jsonObjectBaseData.getJSONObject("base_data");

        JSONObject jsonObjectEnterprisesAgro = new JSONObject(body);
        JSONArray jsonArrayEnterprisesAgro;
        jsonArrayEnterprisesAgro = jsonObjectEnterprisesAgro.getJSONArray("enterprises_buy_agro");

        for (Object listEnterprise : jsonArray) {
            jsonObject = new JSONObject(listEnterprise.toString());
            if (jsonObject.get("tipo").equals("VENDA")) {
                System.out.println("Nome: " + jsonObject.get("nome"));
                System.out.println("CNPJ: " + jsonObject.get("cnpj"));
                System.out.println("Inscricao Estadual: " + jsonObject.get("inscricao_estadual"));
                System.out.println("Data de Abertura: " + jsonObject.get("data_abertura"));
                System.out.println("CEP: " + jsonObject.get("cep"));
                if (jsonObject.has("especialization")) {
                    jsonObject = jsonObject.getJSONObject("especialization");
                    System.out.println("Base Contratual: " + jsonObject.get("base_contratual"));
                    if (jsonObject.has("command")){
                        jsonObject = jsonObject.getJSONObject("command");
                        if (jsonObject.get("type").equals("contrato anual")){
                            System.out.println("Tipo: " + jsonObject.get("type"));
                        }
                    }
                }
            }
        }

        if (jsonObjectBaseData.has("solid")){
            jsonObjectBaseData = jsonObjectBaseData.getJSONObject("solid");
            System.out.println("Base Data: " + jsonObjectBaseData.get("cust"));
        }

        for (Object listEnterpriseAgro : jsonArrayEnterprisesAgro){
            jsonObjectEnterprisesAgro = new JSONObject(listEnterpriseAgro.toString());
            if (jsonObjectEnterprisesAgro.get("tipo").equals("AGRO")){
                System.out.println("Nome da Empresa: " + jsonObjectEnterprisesAgro.get("nome"));
                System.out.println("CNPJ da Empresa: " + jsonObjectEnterprisesAgro.get("cnpj"));
                System.out.println("Inscricao Estadual da Empresa: " + jsonObjectEnterprisesAgro.get("inscricao_estadual"));
                System.out.println("Data de Abertura da Empresa: " + jsonObjectEnterprisesAgro.get("data_abertura"));
                System.out.println("CEP da Empresa: " + jsonObjectEnterprisesAgro.get("cep"));
                System.out.println("Tipo da Empresa: " + jsonObjectEnterprisesAgro.get("tipo"));
                if (jsonObjectEnterprisesAgro.has("especialization")){
                    jsonObjectEnterprisesAgro = jsonObjectEnterprisesAgro.getJSONObject("especialization");
                    System.out.println("Base Contratual: " + jsonObjectEnterprisesAgro.get("base_contratual"));
                    if (jsonObjectEnterprisesAgro.has("command")){
                        jsonObjectEnterprisesAgro = jsonObjectEnterprisesAgro.getJSONObject("command");
                        System.out.println("Tipo(Enterprises) : " + jsonObjectEnterprisesAgro.get("type"));
                    }
                }
            }
        }
    }

}
