package com.github.jeff5m.ifood.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "plate")
@SuppressWarnings({"squid:S1133", "squid:S1123", "squid:S1186"})
public class Plate extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank
    public String name;
    public String description;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    public Restaurant restaurant;
    public BigDecimal price;

    @Deprecated
    public Plate() {
    }

    public Plate(String name, String description, Restaurant restaurant, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.restaurant = restaurant;
        this.price = price;
    }

    public Plate(String name, Restaurant restaurant) {
        this.name = name;
        this.restaurant = restaurant;
    }
}
