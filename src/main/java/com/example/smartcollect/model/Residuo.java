package com.example.smartcollect.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "T_Residuo")
public class Residuo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResiduo;

    private String tipo;
    private String descricao;

    @OneToMany(mappedBy = "residuo")
    private List<Descarte> descartes;
}
