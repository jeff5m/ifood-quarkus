package com.github.jeff5m.ifood.cadastro.dto.prato;

import com.github.jeff5m.ifood.cadastro.dto.restaurante.RestauranteResponseDTO;

import java.math.BigDecimal;

public class PratoResponseDTO {
    public Long id;
    public String nome;
    public String descricao;
    public RestauranteResponseDTO restaurante;
    public BigDecimal preco;
}
