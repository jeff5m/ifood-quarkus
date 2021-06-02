package com.github.jeff5m.ifood.cadastro;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "restaurante")
public class Restaurante extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String proprietario;
    @CNPJ
    public String cnpj;
    @NotBlank
    public String nome;
    @ManyToOne
    public Localizacao localizacao;
    @CreationTimestamp
    public LocalDate dataCriacao;
    @UpdateTimestamp
    public LocalDate dataAtualizacao;
}
