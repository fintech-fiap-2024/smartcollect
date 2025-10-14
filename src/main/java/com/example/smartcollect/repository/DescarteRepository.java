package com.example.smartcollect.repository;

import com.example.smartcollect.model.Descarte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DescarteRepository extends JpaRepository<Descarte, Long> {
    List<Descarte> findByPontoColeta_IdPonto(Long idPonto);

    @Query("SELECT SUM(d.quantidadeKg) FROM Descarte d WHERE d.pontoColeta.idPonto = :idPonto")
    Double getTotalDescartePorPonto(@Param("idPonto") Long idPonto);

    @Query("SELECT d.pontoColeta.idPonto, SUM(d.quantidadeKg) FROM Descarte d GROUP BY d.pontoColeta.idPonto HAVING SUM(d.quantidadeKg) > :limite")
    List<Object[]> findPontosComDescarteAcimaDe(@Param("limite") Double limite);
}
