Feature: Validar o terceiro JSON Aliare AGRO
@Test
  Scenario: Validando o terceiro JSON Aliare AGRO
    Given Que seja efetuado um GET no endpoit do terceiro JSON "http://demo1310142.mockable.io/"
    When Validar e os unicos campos que podem ser null sao "price_sign", "currency", "rating", "category"
    And Sera validado os valores dos campos "tag_list" e product_colors contendo o valor impresso na tela
    And Sera validado caso o campo "rating" seja igual a zero deve ser informado qual o ID
    And Sera validado que o campo "category" nao pode ter o valor invalid
    And Sera validado caso o campo "currency" for igual a 1000, o campo "rating" precisar ser igual ou a cima 9,5 nao pode ultrapassar 10
    Then Sera feita a validacao que os campos "tag_list" e "product_colors" nao pode ter a quantidade maior que que 10