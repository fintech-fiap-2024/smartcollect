package com.example.smartcollect.service;


import com.example.smartcollect.dto.ResiduoDTO;
import com.example.smartcollect.model.Residuo;
import com.example.smartcollect.repository.ResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResiduoService {

    @Autowired
    private ResiduoRepository repository;

    public ResiduoDTO salvar(ResiduoDTO dto) {
        Residuo entidade = toEntity(dto);
        entidade = repository.save(entidade);
        return toDTO(entidade);
    }

    public List<ResiduoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public ResiduoDTO buscarPorId(Long id) {
        Residuo entidade = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resíduo não encontrado"));
        return toDTO(entidade);
    }

    private Residuo toEntity(ResiduoDTO dto) {
        Residuo r = new Residuo();
        r.setIdResiduo(dto.idResiduo);
        r.setTipo(dto.tipo);
        r.setDescricao(dto.descricao);
        return r;
    }

    private ResiduoDTO toDTO(Residuo r) {
        ResiduoDTO dto = new ResiduoDTO();
        dto.idResiduo = r.getIdResiduo();
        dto.tipo = r.getTipo();
        dto.descricao = r.getDescricao();
        return dto;
    }
}
