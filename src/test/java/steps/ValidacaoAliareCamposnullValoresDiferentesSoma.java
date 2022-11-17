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
//            if (newResponse.has("cust")) {
//                iFNull(newResponse, "cust");
//            }
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

    @When("Sera validado que o campo {string} da enterprise_buy que so pode ter o valor VENDA ou NEGOCIO")
    public void seraValidadoQueOCampoDaEnterpriseBuyQueSoPodeTerOValorVENDAOuNEGOCIO(String tipo) {
        JSONArray jsonArrayTipo;
        jsonArrayTipo = response.getJSONArray("enterprises_buy");
        for (Object listTipo : jsonArrayTipo) {
            newResponse = new JSONObject(listTipo.toString());
            if (newResponse.has(tipo)) {
                String getTipo = newResponse.get(tipo).toString();
                if (getTipo.equals("VENDA") || getTipo.equals("NEGOCIO")) {

                } else
                    throw new RuntimeException("O campo Tipo só pode ser VENDA ou NEGOCIO da enterprises_buy, e o campo do cliente: " + newResponse.get("nome") + " esta " + getTipo);
            }
        }
    }

    @When("Sera validado se o campo {string} tiver valor diferente de contrato anual deve ser contabilizado")
    public void seraValidadoSeOCampoTiverValorDiferenteDeContratoAnualDeveSerContabilizado(String type) {
        JSONArray jsonArrayType;
        jsonArrayType = response.getJSONArray("enterprises_buy");
        for (Object listType : jsonArrayType) {
            newResponse = new JSONObject(listType.toString());
            JSONObject json = newResponse.getJSONObject("especialization");
            json = json.getJSONObject("command");
            String typeValidation = json.get(type).toString();
            if (!typeValidation.equals("contrato anual")){
                throw new RuntimeException("O campo type de enterprises_buy tem que ter o valor contrato anual, mas o cliente: " + newResponse.get("nome") + " tem o valor " + typeValidation);
            }
            JSONArray jsonArrayTypeAgro = response.getJSONArray("enterprises_buy_agro");
            for (Object listAgro : jsonArrayTypeAgro){
                newResponse = new JSONObject(listAgro.toString());
                JSONObject jsonAgro = newResponse.getJSONObject("especialization");
                jsonAgro = jsonAgro.getJSONObject("command");
                String getTypeAgro = jsonAgro.get(type).toString();
                if (!getTypeAgro.equals("contrato anual")){
                    throw new RuntimeException("O campo type de enterprises_buy_agro tem que ter o valor contrato anual, mas o cliente: " + newResponse.get("nome") + " tem o valor " + getTypeAgro);
                }
            }

        }

    }

    @Then("Sera validado que o campo tipo nao seja exista valores diferentes de AGRO e AFRE")
    public void seraValidadoQueOCampoTipoNaoSejaExistaValoresDiferentesDeAGROEAFRE() {

        JSONArray jsonAgro;
        jsonAgro = response.getJSONArray("enterprises_buy_agro");

        for (Object listAgro : jsonAgro) {
            newResponse = new JSONObject(listAgro.toString());
            if (newResponse.has("tipo")) {
                String getTipo = newResponse.get("tipo").toString();
                if (getTipo.equals("AGRO") || getTipo.equals("AFRE")) {

                } else
                    throw new RuntimeException("O campo Tipo só pode ser AGRO ou VENDA da enterprises_buy_agro, e o campo do cliente: " + newResponse.get("nome") + " esta " + getTipo);
            }
        }

    }

}
