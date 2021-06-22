package com.github.jeff5m.ifood.cadastro.dto.prato;

import com.github.jeff5m.ifood.cadastro.Prato;
import com.github.jeff5m.ifood.cadastro.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface PratoMapper {

    List<PratoResponseDTO> toListOfPratoResponseDTO(List<Prato> prato);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "restaurante", source = "restauranteId")
    Prato toPrato(AdicionarPratoDTO pratoDTO, Long restauranteId);

    Prato toPrato(AtualizarPratoDTO atualizarPratoDTO, @MappingTarget Prato prato);

    default Restaurante map(Long restauranteId) {
        return Restaurante.findById(restauranteId);
    }
}
