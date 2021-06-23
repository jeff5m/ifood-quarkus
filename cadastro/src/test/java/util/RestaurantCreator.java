package util;

import com.github.jeff5m.ifood.cadastro.Restaurante;
import com.github.jeff5m.ifood.cadastro.dto.LocalizacaoDTO;
import com.github.jeff5m.ifood.cadastro.dto.restaurante.AdicionarRestauranteDTO;

public class RestaurantCreator {
    public static AdicionarRestauranteDTO createValidRestaurantToBeSaved() {
        return new AdicionarRestauranteDTO(
                "Fulano",
                "481091283090",
                "Restaurante a ser salvo",
                new LocalizacaoDTO()
                );
    }

    public static Restaurante createValidRestaurant() {
        return new Restaurante(123L, "Restaurante teste");
    }
}
