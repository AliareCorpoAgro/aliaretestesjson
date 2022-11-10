Feature: Validar Json aliare AGRO

  Scenario: Validando Json aliare AGRO
    Given Que seja efetuado um GET no endpoint daMockable "http://demo7063957.mockable.io/"
    When Validar os unicos campos que podem ser null price_sign,currency,rating,category
    And Sera validado a quantidade dentro do campo "product_colors", sera permitido apenas 20 itens
    And Sera validado a quantidade dentro do campo "tag_list", pode ter apenas 10 itens
    Then Sera feita a validacao que o campo "product_type" nao pode ter o valor em analise