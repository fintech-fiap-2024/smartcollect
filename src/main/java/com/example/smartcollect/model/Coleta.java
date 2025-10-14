package com.example.smartcollect.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_Coleta")
public class Coleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idColeta;

    private LocalDate dataColeta;
    private Double quantidadeKg;

    @ManyToOne
    @JoinColumn(name = "T_Ponto_Coleta_id_ponto")
    private PontoColeta pontoColeta;
}
