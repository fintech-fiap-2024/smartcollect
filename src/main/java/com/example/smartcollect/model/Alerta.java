package com.example.smartcollect.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_Alertas")
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlerta;

    private LocalDate dataAlerta;
    private String mensagem;
    private char resolvido;

    @ManyToOne
    @JoinColumn(name = "T_Ponto_Coleta_id_ponto")
    private PontoColeta pontoColeta;
}
