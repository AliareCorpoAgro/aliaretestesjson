package steps;

import components.RestComponents;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;

import static components.RestComponents.*;

public class AliareAgroTesteAPIJson3 {
    private JSONArray response;

    @Given("Que seja efetuado um GET no endpoit do terceiro JSON {string}")
    public void queSejaEfetuadoUmGETNoEndpoitDoTerceiroJSON(String endpoint) {
        String result = RestConnection(endpoint);
        response = new JSONArray(result);

    }

    @When("Validar e os unicos campos que podem ser null sao {string}, {string}, {string}, {string}")
    public void validarEOsUnicosCamposQuePodemSerNullSao(String string, String string2, String string3, String string4) {
        String[] listSetWordArray = {"id", "brand", "name", "price", "image_link", "product_link", "website_link", "description", "product_type", "tag_list", "created_at", "updated_at", "product_api_url", "api_featured_image", "product_colors"};
        for (Object listArray : response) {
            JSONObject json = new JSONObject(listArray.toString());
            for (Object listNull : listSetWordArray) {
                iFNull(json, listNull.toString());
            }
        }
    }

    @When("Sera validado os valores dos campos {string} e product_colors contendo o valor impresso na tela")
    public void seraValidadoOsValoresDosCamposEProductColorsContendoOValorImpressoNaTela(String string) {
        for (Object listValidation : response) {
            JSONObject jsonValidation = new JSONObject(listValidation.toString());
            if (jsonValidation.has("tag_list")) {
                JSONArray jsonArrayValidation = jsonValidation.getJSONArray("tag_list");
                int validationTagList = jsonArrayValidation.length();
//                System.out.println("O id: " + jsonValidation.get("id") + " tem o valor de tag_list igual a: " + validationTagList);
            }
            if (jsonValidation.has("product_colors")) {
                JSONArray jsonArrayVAlidationProduct = jsonValidation.getJSONArray("product_colors");
                int validationProduct = jsonArrayVAlidationProduct.length();
//                System.out.println("O id: " + jsonValidation.get("id") + " tem o valor do product_colors igual a: " + validationProduct);
            }
        }

    }

    @When("Sera validado caso o campo {string} seja igual a zero deve ser informado qual o ID")
    public void seraValidadoCasoOCampoSejaIgualAZeroDeveSerInformadoQualOID(String string) {
        for (Object listRating : response) {
            JSONObject jsonRating = new JSONObject(listRating.toString());
            if (jsonRating.has("rating")) {
                String rating = jsonRating.get("rating").toString();
                if (rating.equals("0")) {
//                    System.out.println("O id:" + jsonRating.get("id") + " contem rating: " + rating);
                }
            }
        }
    }

    @When("Sera validado que o campo {string} nao pode ter o valor invalid")
    public void seraValidadoQueOCampoNaoPodeTerOValorInvalid(String string) {
        for (Object listCategory : response) {
            JSONObject jsonCategory = new JSONObject(listCategory.toString());
            if (jsonCategory.has("category")) {
                String category = jsonCategory.get("category").toString();
                if (category.equals("invalid")) {
//                    System.out.println("O id: " + jsonCategory.get("id") + " tem o valor " + category);
                }
            }
        }

    }

    @When("Sera validado caso o campo {string} for igual a {int}, o campo {string} precisar ser igual ou a cima {double} nao pode ultrapassar {int}")
    public void seraValidadoCasoOCampoForIgualAOCampoPrecisarSerIgualOuACimaNaoPodeUltrapassar(String string, Integer int1, String string2, Double double1, Integer int2) {
        for (Object listValidation : response) {
            JSONObject jsonValidation = new JSONObject(listValidation.toString());
            if (jsonValidation.has("currency") && jsonValidation.has("rating")) {
                if (jsonValidation.get("rating").getClass().getSimpleName().equals("Integer") || jsonValidation.get("rating").getClass().getSimpleName().equals("BigDecimal")) {
                    if (jsonValidation.get("currency").getClass().getSimpleName().equals("Integer") || jsonValidation.get("Integer").getClass().getSimpleName().equals("BigDecimal")) {
                        String currency = jsonValidation.get("currency").toString();
                        int numberInt = Integer.parseInt(currency);
                        String rating = jsonValidation.get("rating").toString();
                        double numberDouble = Double.parseDouble(rating);
                        System.out.println(numberInt);
                        if (numberInt == 1000) {
                            if (numberDouble >= 9.5 && numberDouble < 10.0) {
                            } else {
                                System.out.println("O id: " + jsonValidation.get("id") + " esta irregular e precisa de uma correcao");
                            }
                        }
                    }
                }
            }
        }
    }

    @Then("Sera feita a validacao que os campos {string} e {string} nao pode ter a quantidade maior que que {int}")
    public void seraFeitaAValidacaoQueOsCamposENaoPodeTerAQuantidadeMaiorQueQue(String string, String string2, Integer int1) {

    }
}
