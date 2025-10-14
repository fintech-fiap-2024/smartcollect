package com.example.smartcollect.service;

import com.example.smartcollect.dto.PontoColetaDTO;
import com.example.smartcollect.model.PontoColeta;
import com.example.smartcollect.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontoColetaService {
    @Autowired
    private PontoColetaRepository repository;

    public List<PontoColetaDTO> listarTodos() {
        return repository.findAll().stream().map(this::toDTO).toList();
    }

    public PontoColetaDTO salvar(PontoColetaDTO dto) {
        PontoColeta ponto = new PontoColeta();
        ponto.setNome(dto.nome);
        ponto.setEndereco(dto.endereco);
        ponto.setCapacidadeTotal(dto.capacidadeTotal);
        return toDTO(repository.save(ponto));
    }

    private PontoColetaDTO toDTO(PontoColeta ponto) {
        PontoColetaDTO dto = new PontoColetaDTO();
        dto.idPonto = ponto.getIdPonto();
        dto.nome = ponto.getNome();
        dto.endereco = ponto.getEndereco();
        dto.capacidadeTotal = ponto.getCapacidadeTotal();
        return dto;
    }
}