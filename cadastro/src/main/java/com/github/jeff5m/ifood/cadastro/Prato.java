package com.github.jeff5m.ifood.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "prato")
@SuppressWarnings({"squid:S1133", "squid:S1123", "squid:S1186"})
public class Prato extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotBlank
    public String nome;
    public String descricao;
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    public Restaurante restaurante;
    public BigDecimal preco;

    @Deprecated
    public Prato() {
    }

    public Prato(String nome, Restaurante restaurante) {
        this.nome = nome;
        this.restaurante = restaurante;
    }
}
