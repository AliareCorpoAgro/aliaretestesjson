package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;

import static components.RestComponents.*;

public class ValidacaoAliareUnicoscamposnullCamposvalosdiferentes {
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
                System.out.println("O id: " + jsonValidation.get("id") + " tem o valor de tag_list igual a: " + validationTagList);
            }
            if (jsonValidation.has("product_colors")) {
                JSONArray jsonArrayVAlidationProduct = jsonValidation.getJSONArray("product_colors");
                int validationProduct = jsonArrayVAlidationProduct.length();
                System.out.println("O id: " + jsonValidation.get("id") + " tem o valor do product_colors igual a: " + validationProduct);
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
                    System.out.println("O id:" + jsonRating.get("id") + " contem rating: " + rating);
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
                    throw new RuntimeException("O id: " + jsonCategory.get("id") + " do cliente " + jsonCategory.get("name") + " tem o valor " + category);
                }
            }
        }

    }

    @When("Sera validado caso o campo {string} for igual a {int}, o campo {string} precisar ser igual ou a cima {double} nao pode ultrapassar {int}")
    public void seraValidadoCasoOCampoForIgualAOCampoPrecisarSerIgualOuACimaNaoPodeUltrapassar(String string, Integer int1, String string2, Double double1, Integer int2) {
        for (Object listValidation : response) {
            JSONObject jsonValidation = new JSONObject(listValidation.toString());
            String currencyClass = jsonValidation.get("currency").getClass().getSimpleName();
            String ratingClass = jsonValidation.get("rating").getClass().getSimpleName();
            if (!currencyClass.equals("Null")) {
                if (currencyClass.equals("Integer") && ratingClass.equals("BigDecimal")) {
                    int currencyInt = (int) jsonValidation.get("currency");
                    BigDecimal ratingBigDecimal = (BigDecimal) jsonValidation.get("rating");
                    double ratingDouble = ratingBigDecimal.doubleValue();
                    if (currencyInt == 1000 && ratingDouble >= 9.5 && ratingDouble < 10) {

                    } else
                        throw new RuntimeException("O id: " + jsonValidation.get("id") + " esta irregular e precisa de uma correcao, pois o campo currency e rating nao esta com o valor surgerido");
                }
                if (currencyClass.equals("Integer") && ratingClass.equals("Integer")) {
                    int currencyInt = (int) jsonValidation.get("currency");
                    int ratingInt = (int) jsonValidation.get("rating");
                    if (currencyInt == 1000 && ratingInt >= 9.5 && ratingInt < 10) {

                    } else
                        throw new RuntimeException("O id: " + jsonValidation.get("id") + " esta irregular e precisa de uma correcao, pois o campo currency e rating nao esta com o valor surgerido");
                }
            }
        }
    }

    @Then("Sera feita a validacao que os campos {string} e {string} nao pode ter a quantidade maior que que {int}")
    public void seraFeitaAValidacaoQueOsCamposENaoPodeTerAQuantidadeMaiorQueQue(String string, String string2, Integer int1) {
        for (Object listValidationCamp : response) {
            JSONObject jsonValidationCamp = new JSONObject(listValidationCamp.toString());
            JSONArray jsonArrayTagList = jsonValidationCamp.getJSONArray("tag_list");
            int tagList = jsonArrayTagList.length();
            JSONArray jsonArrayProductColors = jsonValidationCamp.getJSONArray("product_colors");
            int productColors = jsonArrayProductColors.length();
            if (tagList > 10) {
                if (productColors > 10) {
                    throw new RuntimeException("O id: " + jsonValidationCamp.get("id") + " tem os campos tag_list e products_colors maior do que 10");
                }
            }
        }

    }
}
