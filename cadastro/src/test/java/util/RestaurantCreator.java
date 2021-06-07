package util;

import com.github.jeff5m.ifood.cadastro.Restaurante;

public class RestaurantCreator {
    public static Restaurante createValidRestaurantToBeSaved() {
        return new Restaurante("Restaurante a ser salvo", "Novo ID do keyclok");
    }

    public static Restaurante createValidRestaurant() {
        return new Restaurante(123L, "Restaurante teste");
    }
}
