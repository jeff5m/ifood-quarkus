package com.github.jeff5m.ifood.cadastro.dto;

import com.github.jeff5m.ifood.cadastro.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "nome", source = "nomeFantasia")
    Restaurante toRestaurante(AdicionarRestauranteDTO restauranteDTO);
}
