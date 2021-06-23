package com.github.jeff5m.ifood.cadastro.dto.restaurante;

import com.github.jeff5m.ifood.cadastro.dto.LocalizacaoDTO;

public class AdicionarRestauranteDTO {
    public String proprietario;
    public String cnpj;
    public String nomeFantasia;
    public LocalizacaoDTO localizacao;

    public AdicionarRestauranteDTO() {
    }

    public AdicionarRestauranteDTO(String proprietario, String cnpj, String nomeFantasia, LocalizacaoDTO localizacao) {
        this.proprietario = proprietario;
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.localizacao = localizacao;
    }
}
