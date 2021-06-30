package com.github.jeff5m.ifood.cadastro.dto.restaurante;

import com.github.jeff5m.ifood.cadastro.dto.LocalizacaoDTO;
import com.github.jeff5m.ifood.cadastro.validations.Unique;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AdicionarRestauranteDTO {

    @NotBlank
    public String proprietario;

    @CNPJ
    @NotEmpty
    @Unique(message = "Já existe um restaurante cadastrado com o cnpj informado")
    public String cnpj;

    @Size(min = 3, max = 30, message = "Tamanho do nome deve ter de 2 até 100 caracteres")
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
