package steps;

import components.RestComponents;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import static components.RestComponents.RestConnection;
import static components.RestComponents.iFNull;

public class ValidacaoAliareCamposnullValoresDiferentes {
    private JSONObject response;
    private JSONObject newResponse;

    @Given("Que seja efetuado um GET no endpoint {string}")
    public void queSejaEfetuadoUmGETNoEndpoint(String endpoint) {
        String result = RestConnection(endpoint);
        response = new JSONObject(result);
    }

    @When("For efetuado validacao de campos para que nao sejam null")
    public void forEfetuadoValidacaoDeCamposParaQueNaoSejamNull() {

        JSONArray jsonArray;
        jsonArray = response.getJSONArray("enterprises_buy");
        iFNull(response, "enterprises_buy");

        for (Object listEnterprises : jsonArray) {
            newResponse = new JSONObject(listEnterprises.toString());
            if (newResponse.has("especialization")) {
                iFNull(newResponse, "especialization");
                newResponse = newResponse.getJSONObject("especialization");
                if (newResponse.has("command")) {
                    iFNull(newResponse, "command");
                    newResponse = newResponse.getJSONObject("command");
                    iFNull(newResponse, "type");
                }
            }
        }
        JSONArray jsonArrayAgro;
        jsonArrayAgro = response.getJSONArray("enterprises_buy_agro");
        iFNull(response, "enterprises_buy_agro");

        for (Object listAgro : jsonArrayAgro) {
            newResponse = new JSONObject(listAgro.toString());
            if (newResponse.has("especialization")) {
                iFNull(newResponse, "especialization");
                newResponse = newResponse.getJSONObject("especialization");
                if (newResponse.has("command")) {
                    iFNull(newResponse, "command");
                    newResponse = newResponse.getJSONObject("command");
//                    iFNull(newResponse, "type");
                }
            }
        }
    }

    @When("Sera validado a soma total dos campos {string}")
    public void seraValidadoASomaTotalDosCampos(String string) {
        JSONArray jsonArray;
        jsonArray = response.getJSONArray("enterprises_buy");
        int valorTotal = 0;
        for (Object listEnterprises : jsonArray) {
            newResponse = new JSONObject(listEnterprises.toString());
            if (newResponse.has("especialization")) {
                newResponse = newResponse.getJSONObject("especialization");
                int sam = (int) newResponse.get("base_contratual");
                valorTotal = valorTotal + sam;
            }
        }
        System.out.println("O valor total do array enterprises_buy_agro, path: base_contratual: " + valorTotal);
    }

    @Then("Sera validado que o campo tipo nao seja exista valores diferentes de AGRO e VENDA")
    public void seraValidadoQueOCampoTipoNaoSejaExistaValoresDiferentesDeAGROEVENDA() {

    }
}
