package util;

import com.github.jeff5m.ifood.cadastro.Prato;

public class PlateCreator {
    public static Prato createValidPlateToBeSaved() {
        return new Prato("Prato a ser salvo", RestaurantCreator.createValidRestaurant());
    }
}
