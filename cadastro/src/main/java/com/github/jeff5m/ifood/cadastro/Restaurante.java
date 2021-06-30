package com.github.jeff5m.ifood.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "restaurante")
@SuppressWarnings({"squid:S1133", "squid:S1123", "squid:S1186"})
public class Restaurante extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String proprietario;

    public String cnpj;

    @NotBlank
    public String nome;

    @OneToOne(cascade = CascadeType.ALL)
    public Localizacao localizacao;

    @CreationTimestamp
    public LocalDate dataCriacao;

    @UpdateTimestamp
    public LocalDate dataAtualizacao;

    @Deprecated
    public Restaurante() {
    }

    public Restaurante(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Restaurante(String nome, String proprietario) {
        this.nome = nome;
        this.proprietario = proprietario;
    }

    public static Restaurante findByCnpj(String cnpj) {
        return find("cnpj", cnpj).firstResult();
    }
}
