package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import static components.RestComponents.PathGet;
import static components.RestComponents.RestConnection;

public class ValidacaoAliareCamposnullValoresDiferentes {
    private JSONObject response;

    @Given("Que seja efetuado um GET no endpoint {string}")
    public void queSejaEfetuadoUmGETNoEndpoint(String endpoint) {
        String result = RestConnection(endpoint);
        response = new JSONObject(result);
    }

    @When("For efetuado validacao de campos para que nao sejam null")
    public void forEfetuadoValidacaoDeCamposParaQueNaoSejamNull() {
        JSONArray jsonArray;
        jsonArray = response.getJSONArray("enterprises_buy");
        Assert.assertNotNull(jsonArray);

        JSONArray jsonArrayAgro;
        jsonArrayAgro = response.getJSONArray("enterprises_buy_agro");
        Assert.assertNotNull(jsonArrayAgro);

        for (Object listEnterprises : jsonArray) {
            response = new JSONObject(listEnterprises.toString());
            Assert.assertNotNull(response.get("nome"));
            Assert.assertNotNull(response.get("cnpj"));
            Assert.assertNotNull(response.get("inscricao_estadual"));
            Assert.assertNotNull(response.get("data_abertura"));
            Assert.assertNotNull(response.get("cep"));
            Assert.assertNotNull(response.get("tipo"));
            if (response.has("especialization")) {
                response = response.getJSONObject("especialization");
                Assert.assertNotNull(response);
                Assert.assertNotNull(response.get("base_contratual"));
                if (response.has("command")) {
                    response = response.getJSONObject("command");
                    Assert.assertNotNull(response);
                    Assert.assertNotNull(response.get("type"));
                }
            }
        }

        for (Object listAgro : jsonArrayAgro) {
            response = new JSONObject(listAgro.toString());
            Assert.assertNotNull(response.get("nome"));
            Assert.assertNotNull(response.get("cnpj"));
            Assert.assertNotNull(response.get("inscricao_estadual"));
            Assert.assertNotNull(response.get("data_abertura"));
            Assert.assertNotNull(response.get("cep"));
            Assert.assertNotNull(response.get("tipo"));
            if (response.has("especialization")) {
                response = response.getJSONObject("especialization");
                Assert.assertNotNull(response);
                Assert.assertNotNull(response.get("base_contratual"));
                if (response.has("command")){
                    response = response.getJSONObject("command");
                    Assert.assertNotNull(response.get("type"));
                }

            }
        }
    }

    @When("Sera validado a soma total dos campos {string}")
    public void seraValidadoASomaTotalDosCampos(String string) {
        String resultPhat = PathGet(string);
        response = new JSONObject(resultPhat);
        System.out.println(response);


    }

    @Then("Sera validado que o campo tipo nao seja exista valores diferentes de AGRO e VENDA")
    public void seraValidadoQueOCampoTipoNaoSejaExistaValoresDiferentesDeAGROEVENDA() {

    }
}
