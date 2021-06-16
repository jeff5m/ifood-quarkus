package com.github.jeff5m.ifood.cadastro.dto.restaurante;

import com.github.jeff5m.ifood.cadastro.dto.LocalizacaoDTO;

public class RestauranteResponseDTO {
    public Long id;
    public String proprietario;
    public String cnpj;
    public String nomeFantasia;
    public LocalizacaoDTO localizacao;
    public String dataCriacao;
}
