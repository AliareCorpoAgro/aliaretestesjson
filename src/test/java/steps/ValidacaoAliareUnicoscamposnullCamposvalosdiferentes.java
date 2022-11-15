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

    @When("Validar e os unicos campos que podem ser null sao price_sign, currency, rating, category")
    public void validarEOsUnicosCamposQuePodemSerNullSaoPriceSignCurrencyRatingCategory() {
        String[] listSetWordArray = {"id", "brand", "name", "price", "image_link", "product_link", "website_link", "description", "product_type", "tag_list", "created_at", "updated_at", "product_api_url", "api_featured_image", "product_colors"};
        for (Object listArray : response) {
            JSONObject json = new JSONObject(listArray.toString());
            for (Object listNull : listSetWordArray) {
                iFNull(json, listNull.toString());
            }
        }
    }

    @When("Sera validado os valores dos campos {string} e {string} contendo o valor impresso na tela")
    public void seraValidadoOsValoresDosCamposEContendoOValorImpressoNaTela(String tagList, String productColors) {
        for (Object listValidation : response) {
            JSONObject jsonValidation = new JSONObject(listValidation.toString());
            if (jsonValidation.has(tagList)) {
                JSONArray jsonArrayValidation = jsonValidation.getJSONArray(tagList);
                int validationTagList = jsonArrayValidation.length();
                System.out.println("O id: " + jsonValidation.get("id") + " tem o valor de tag_list igual a: " + validationTagList);
            }
            if (jsonValidation.has(productColors)) {
                JSONArray jsonArrayVAlidationProduct = jsonValidation.getJSONArray(productColors);
                int validationProduct = jsonArrayVAlidationProduct.length();
                System.out.println("O id: " + jsonValidation.get("id") + " tem o valor do product_colors igual a: " + validationProduct);
            }
        }

    }

    @When("Sera validado caso o campo {string} seja igual a zero deve ser informado qual o ID")
    public void seraValidadoCasoOCampoSejaIgualAZeroDeveSerInformadoQualOID(String ratingID) {
        for (Object listRating : response) {
            JSONObject jsonRating = new JSONObject(listRating.toString());
            if (jsonRating.has("rating")) {
                String rating = jsonRating.get(ratingID).toString();
                if (rating.equals("0")) {
                    System.out.println("O id:" + jsonRating.get("id") + " contem rating: " + rating);
                }
            }
        }
    }

    @When("Sera validado que o campo {string} nao pode ter o valor invalid")
    public void seraValidadoQueOCampoNaoPodeTerOValorInvalid(String categoryInvalid) {
        for (Object listCategory : response) {
            JSONObject jsonCategory = new JSONObject(listCategory.toString());
            if (jsonCategory.has(categoryInvalid)) {
                String category = jsonCategory.get(categoryInvalid).toString();
                if (category.equals("invalid")) {
                    throw new RuntimeException("O id: " + jsonCategory.get("id") + " do cliente " + jsonCategory.get("name") + " tem o valor " + category);
                }
            }
        }

    }

    @When("Sera validado caso o campo {string} for igual a {int}, o campo {string} precisar ser igual ou a cima {double} nao pode ultrapassar {int}")
    public void seraValidadoCasoOCampoForIgualAOCampoPrecisarSerIgualOuACimaNaoPodeUltrapassar(String currency, Integer number, String rating, Double numberRatingDouble, Integer numberRating) {
        for (Object listValidation : response) {
            JSONObject jsonValidation = new JSONObject(listValidation.toString());
            String currencyClass = jsonValidation.get(currency).getClass().getSimpleName();
            String ratingClass = jsonValidation.get(rating).getClass().getSimpleName();
            if (!currencyClass.equals("Null")) {
                if (currencyClass.equals("Integer") && ratingClass.equals("BigDecimal")) {
                    int currencyInt = (int) jsonValidation.get(currency);
                    BigDecimal ratingBigDecimal = (BigDecimal) jsonValidation.get(rating);
                    double ratingDouble = ratingBigDecimal.doubleValue();
                    if (currencyInt == number && ratingDouble >= numberRatingDouble && ratingDouble < numberRating) {

                    } else
                        throw new RuntimeException("O id: " + jsonValidation.get("id") + " esta irregular e precisa de uma correcao, pois o campo currency e rating nao esta com o valor surgerido");
                }
                if (currencyClass.equals("Integer") && ratingClass.equals("Integer")) {
                    int currencyInt = (int) jsonValidation.get(currency);
                    int ratingInt = (int) jsonValidation.get(rating);
                    if (currencyInt == number && ratingInt >= numberRatingDouble && ratingInt < numberRating) {

                    } else
                        throw new RuntimeException("O id: " + jsonValidation.get("id") + " esta irregular e precisa de uma correcao, pois o campo currency e rating nao esta com o valor surgerido");
                }
            }
        }
    }

    @Then("Sera feita a validacao que os campos {string} e {string} nao pode ter a quantidade maior que que {int}")
    public void seraFeitaAValidacaoQueOsCamposENaoPodeTerAQuantidadeMaiorQueQue(String tagLists, String produtColors, Integer number) {
        for (Object listValidationCamp : response) {
            JSONObject jsonValidationCamp = new JSONObject(listValidationCamp.toString());
            JSONArray jsonArrayTagList = jsonValidationCamp.getJSONArray(tagLists);
            int tagList = jsonArrayTagList.length();
            JSONArray jsonArrayProductColors = jsonValidationCamp.getJSONArray(produtColors);
            int productColors = jsonArrayProductColors.length();
            if (tagList > number) {
                if (productColors > number) {
                    throw new RuntimeException("O id: " + jsonValidationCamp.get("id") + " tem os campos tag_list e products_colors maior do que 10");
                }
            }
        }

    }
}
