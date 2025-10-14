package com.example.smartcollect.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@Table(name = "T_Descarte")
public class Descarte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDescarte;

    private Double quantidadeKg;
    private LocalDate dataDescarte;

    @ManyToOne
    @JoinColumn(name = "T_Ponto_Coleta_id_ponto")
    private PontoColeta pontoColeta;

    @ManyToOne
    @JoinColumn(name = "T_Residuo_id_residuo")
    private Residuo residuo;
}
