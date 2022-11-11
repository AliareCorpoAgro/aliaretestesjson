Feature: Validar Json Aliare, campos null, soma do campo Base Contratual, campo TIPO com valor diferente

  Scenario: Validando Json, campos null, soma do campo Base Contratual, campo TIPO com valor diferente
    Given Que seja efetuado um GET no endpoint "http://demo3675470.mockable.io/"
    When For efetuado validacao de campos para que nao sejam null
    And Sera validado a soma total dos campos "base_contratual"
    Then Sera validado que o campo tipo nao seja exista valores diferentes de AGRO e VENDA