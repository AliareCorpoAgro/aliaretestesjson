Feature: Validar Json Aliare, campos null, soma do campo Base Contratual, campo TIPO com valor diferente
  @Test
  Scenario: Validando Json, campos null, soma do campo Base Contratual, campo TIPO com valor diferente
    Given Que seja efetuado um GET no endpoint "http://demo3675470.mockable.io/"
    When For efetuado validacao de campos para que nao sejam null
    And Sera validado a soma total dos campos "base_contratual"
    And Sera validado que o campo "tipo" da enterprise_buy que so pode ter o valor VENDA ou NEGOCIO
    And Sera validado se o campo "type" tiver valor diferente de contrato anual deve ser contabilizado
    Then Sera validado que o campo tipo nao seja exista valores diferentes de AGRO e AFRE