package util;

import com.github.jeff5m.ifood.cadastro.Restaurant;

public class RestaurantCreator {
    public static Restaurant createValidRestaurantToBeSaved() {
        return new Restaurant("Restaurant To Be Saved", "New Id KeyClok");
    }

    public static Restaurant createValidRestaurant() {
        return new Restaurant(123L, "Restaurant Test");
    }
}
