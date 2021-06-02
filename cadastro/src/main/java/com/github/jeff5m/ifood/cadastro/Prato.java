package com.github.jeff5m.ifood.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "prato")
public class Prato extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    public String nome;
    @NotBlank
    public String descricao;
    @ManyToOne
    public Restaurante restaurante;
    public BigDecimal preco;
}
