package com.example.smartcollect.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "T_Ponto_Coleta")
public class PontoColeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPonto;

    private String nome;
    private String endereco;
    private Double capacidadeTotal;

    @OneToMany(mappedBy = "pontoColeta")
    private List<Coleta> coletas;

    @OneToMany(mappedBy = "pontoColeta")
    private List<Alerta> alertas;

    @OneToMany(mappedBy = "pontoColeta")
    private List<Descarte> descartes;
}

