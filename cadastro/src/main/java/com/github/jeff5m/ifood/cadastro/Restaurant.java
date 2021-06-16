package com.github.jeff5m.ifood.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "restaurant")
@SuppressWarnings({"squid:S1133", "squid:S1123", "squid:S1186"})
public class Restaurant extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String owner;
    @CNPJ
    public String cnpj;
    @NotBlank
    public String name;
    @ManyToOne
    public Localization localization;
    @CreationTimestamp
    public LocalDate createdAt;
    @UpdateTimestamp
    public LocalDate updatedAt;

    @Deprecated
    public Restaurant() {
    }

    public Restaurant(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Restaurant(String name, String owner) {
        this.name = name;
        this.owner = owner;
    }
}
