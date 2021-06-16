package util;

import com.github.jeff5m.ifood.cadastro.Plate;

public class PlateCreator {
    public static Plate createValidPlateToBeSaved() {
        return new Plate("Plate To Be Saved", RestaurantCreator.createValidRestaurant());
    }
}
