package steps;

import io.cucumber.java.en.*;
import org.json.JSONArray;
import org.json.JSONObject;

import static components.RestComponents.RestConnection;
import static components.RestComponents.iFNull;

public class ValidacaoAliareCamposnullValoresDiferentesSoma {
    private JSONObject response;
    private JSONObject newResponse;

    @Given("Que seja efetuado um GET no endpoint {string}")
    public void queSejaEfetuadoUmGETNoEndpoint(String endpoint) {
        String result = RestConnection(endpoint);
        response = new JSONObject(result);
    }

    @When("For efetuado validacao de campos para que nao sejam null")
    public void forEfetuadoValidacaoDeCamposParaQueNaoSejamNull() {
//        System.out.println(response.get("corporation"));
        iFNull(response, "corporation");
        JSONArray jsonArray;
        jsonArray = response.getJSONArray("enterprises_buy");
        iFNull(response, "enterprises_buy");

        String[] listEnterprisesBuyNull = {"nome", "cnpj", "inscricao_estadual", "data_abertura", "cep", "tipo", "especialization", "command", "type", "base_contratual"};

        for (Object listEnterprises : jsonArray) {
            newResponse = new JSONObject(listEnterprises.toString());
            for (Object listNull : listEnterprisesBuyNull) {
                iFNull(newResponse, listNull.toString());
            }
        }

        newResponse = response.getJSONObject("base_data");
        iFNull(newResponse, "base_data");
        if (newResponse.has("solid")) {
            newResponse = newResponse.getJSONObject("solid");
            iFNull(newResponse, "solid");
            if (newResponse.has("cust")) {
                iFNull(newResponse, "cust");
            }
        }

        JSONArray jsonArrayAgro;
        jsonArrayAgro = response.getJSONArray("enterprises_buy_agro");
        iFNull(response, "enterprises_buy_agro");

        String[] listEnterpriseBuyAgro = {"nome", "cnpj", "inscricao_estadual", "data_abertura", "cep", "tipo", "especialization", "command", "type", "base_contratual"};

        for (Object listAgro : jsonArrayAgro) {
            newResponse = new JSONObject(listAgro.toString());
            for (Object listNullAgro : listEnterpriseBuyAgro) {
                iFNull(newResponse, listNullAgro.toString());
            }
        }
    }

    @When("Sera validado a soma total dos campos {string}")
    public void seraValidadoASomaTotalDosCampos(String baseContratual) {
        JSONArray jsonArray;
        jsonArray = response.getJSONArray("enterprises_buy");

        int valueTotal = 0;
        for (Object listEnterprises : jsonArray) {
            newResponse = new JSONObject(listEnterprises.toString());
            if (newResponse.has("especialization")) {
                newResponse = newResponse.getJSONObject("especialization");
                int sam = (int) newResponse.get(baseContratual);
                valueTotal = valueTotal + sam;

            }
        }
        System.out.println("O valor do array enterprises_buy, path: base_contratual: " + valueTotal);

        JSONArray jsonArrayAgro;
        jsonArrayAgro = response.getJSONArray("enterprises_buy_agro");

        int valueTotalAgro = 0;
        for (Object listAgro : jsonArrayAgro) {
            newResponse = new JSONObject(listAgro.toString());
            if (newResponse.has("especialization")) {
                newResponse = newResponse.getJSONObject("especialization");
                int samAgro = (int) newResponse.get(baseContratual);
                valueTotalAgro = valueTotalAgro + samAgro;
            }
        }
        System.out.println("O valor do array enterprises_buy_agro, path: base_contratual: " + valueTotalAgro);
    }

    @Then("Sera validado que o campo tipo nao seja exista valores diferentes de AGRO e VENDA")
    public void seraValidadoQueOCampoTipoNaoSejaExistaValoresDiferentesDeAGROEVENDA() {
        JSONArray jsonArray;
        jsonArray = response.getJSONArray("enterprises_buy");

        for (Object listEnterprises : jsonArray) {
            newResponse = new JSONObject(listEnterprises.toString());
            if (newResponse.has("especialization")) {
                newResponse = newResponse.getJSONObject("especialization");
            }
        }
        JSONArray jsonAgro;
        jsonAgro = response.getJSONArray("enterprises_buy_agro");

        for (Object listAgro : jsonAgro) {
            newResponse = new JSONObject(listAgro.toString());
            if (newResponse.has("tipo")) {
                String getTipo = newResponse.get("tipo").toString();
                if (!getTipo.equals("AGRO") || getTipo.equals("VENDA")) {
                    throw new RuntimeException("O campo Tipo só pode ser AGRO ou VENDA, e o campo do cliente: " + newResponse.get("nome") + " esta " + getTipo);

                }
            }
        }

    }

}
