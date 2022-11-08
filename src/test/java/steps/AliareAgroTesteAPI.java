package steps;

import components.RestComponents;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;

import static components.RestComponents.*;

public class AliareAgroTesteAPI {
    private JSONArray response;

    @Given("Que seja efetuado um GET no endpoint daMockable {string}")
    public void queSejaEfetuadoUmGETNoEndpointDaMockable(String endpoint) {
        String inicializationTest = RestConnection(endpoint);
        response = new JSONArray(inicializationTest);
    }

    @When("Validar os unicos campos que podem ser null price_sign,currency,rating,category")
    public void validarOsUnicosCamposQuePodemSerNullPriceSignCurrencyRatingCategory() {
        String[] listVerificationNull = {"id", "brand", "name", "price", "image_link", "product_link", "website_link", "description", "product_type", "tag_list", "created_at", "updated_at", "product_api_url", "api_featured_image", "product_colors"};
        for (Object listArray : response){
            JSONObject json = new JSONObject(listArray.toString());
            for (Object listNull : listVerificationNull){
                iFNull(json, listNull.toString());
            }
        }
    }

    @When("Sera validado a quantidade dentro do campo {string}, sera permitido apenas {int} itens")
    public void seraValidadoAQuantidadeDentroDoCampoSeraPermitidoApenasItens(String string, Integer int1) {
        for (Object listProductColors : response) {
            JSONObject jsonObject = new JSONObject(listProductColors.toString());
            if (jsonObject.has("product_colors")) {
                JSONArray jsonArray = jsonObject.getJSONArray("product_colors");
                int enterprise = jsonArray.length();
                System.out.println("A empresa " + jsonObject.get("name") + " tem: " + enterprise + " itens dentro do campo product_colors");
                if (jsonArray.length() > 20) {
                    throw new RuntimeException("Só é permitido 20 itens para cada empresa:\n" + jsonObject.get("name") + " tem " + jsonArray.length() + " objetos");
                }
            }
        }
    }

    @When("Sera validado a quantidade dentro do campo {string}, pode ter apenas {int} itens")
    public void seraValidadoAQuantidadeDentroDoCampoPodeTerApenasItens(String string, Integer int1) {
        for (Object listTagListArray : response) {
            JSONObject jsonTagList = new JSONObject(listTagListArray.toString());
            if (jsonTagList.has("tag_list")) {
                JSONArray jsonArrayTagList = jsonTagList.getJSONArray("tag_list");
                int enterprise = jsonArrayTagList.length();
                System.out.println("A empresa " + jsonTagList.get("name") + " tem: " + enterprise + " itens dentro do campo tag_list");
                if (jsonArrayTagList.length() > 10) {
                    throw new RuntimeException("Só é permitido 10 itens para cada empresa:\n" + jsonTagList.get("name") + " tem " + jsonArrayTagList.length() + " objetos");
                }
            }
        }
    }

    @Then("Sera feita a validacao que o campo {string} nao pode ter o valor em analise")
    public void seraFeitaAValidacaoQueOCampoNaoPodeTerOValorEmAnalise(String string) {
        for (Object listProductTipo : response) {
            JSONObject jsonProductTipo = new JSONObject(listProductTipo.toString());
            if (jsonProductTipo.get("product_type").equals("em analise")) {
                throw new RuntimeException("O campo não pode tá em analise:\n" + jsonProductTipo.get("name"));
            }
        }
    }
}
