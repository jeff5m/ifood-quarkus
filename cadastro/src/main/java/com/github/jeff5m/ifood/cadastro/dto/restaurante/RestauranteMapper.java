package com.github.jeff5m.ifood.cadastro.dto.restaurante;

import com.github.jeff5m.ifood.cadastro.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "nome", source = "nomeFantasia")
    Restaurante toRestaurante(AdicionarRestauranteDTO restauranteDTO);

    @Mapping(target = "proprietario", ignore = true)
    @Mapping(target = "localizacao", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "dataAtualizacao", ignore = true)
    @Mapping(target = "cnpj", ignore = true)
    @Mapping(target = "nome", source = "nomeFantasia")
    Restaurante toRestaurante(AtualizarRestauranteDTO atualizarRestauranteDTO, @MappingTarget Restaurante restaurante );

    List<RestauranteResponseDTO> toListOfRestaurantResponseDTO(List<Restaurante> restaurantes);

}
