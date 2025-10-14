package com.example.smartcollect.service;

import com.example.smartcollect.dto.DescarteDTO;
import com.example.smartcollect.dto.QuantidadeTotalDTO;
import com.example.smartcollect.model.Descarte;
import com.example.smartcollect.repository.DescarteRepository;
import com.example.smartcollect.repository.PontoColetaRepository;
import com.example.smartcollect.repository.ResiduoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DescarteService {
    @Autowired
    private DescarteRepository descarteRepo;

    @Autowired
    private PontoColetaRepository pontoRepo;

    @Autowired
    private ResiduoRepository residuoRepo;

    public List<DescarteDTO> listarTodos() {
        return descarteRepo.findAll().stream().map(this::toDTO).toList();
    }

    public DescarteDTO salvar(DescarteDTO dto) {
        Descarte d = new Descarte();
        d.setQuantidadeKg(dto.quantidadeKg);
        d.setDataDescarte(dto.dataDescarte);
        d.setPontoColeta(pontoRepo.findById(dto.pontoColetaId).orElseThrow());
        d.setResiduo(residuoRepo.findById(dto.residuoId).orElseThrow());
        return toDTO(descarteRepo.save(d));
    }

    private DescarteDTO toDTO(Descarte d) {
        DescarteDTO dto = new DescarteDTO();
        dto.idDescarte = d.getIdDescarte();
        dto.quantidadeKg = d.getQuantidadeKg();
        dto.dataDescarte = d.getDataDescarte();
        dto.pontoColetaId = d.getPontoColeta().getIdPonto();
        dto.residuoId = d.getResiduo().getIdResiduo();
        return dto;
    }
    public List<DescarteDTO> listarPorPonto(Long idPonto) {
        return descarteRepo.findByPontoColeta_IdPonto(idPonto).stream().map(this::toDTO).toList();
    }

    public QuantidadeTotalDTO getQuantidadeTotalPorPonto(Long idPonto) {
        Double total = descarteRepo.getTotalDescartePorPonto(idPonto);
        return new QuantidadeTotalDTO(idPonto, total != null ? total : 0);
    }

    public List<QuantidadeTotalDTO> listarPontosComDescarteAcimaDe(Double limite) {
        return descarteRepo.findPontosComDescarteAcimaDe(limite).stream()
                .map(obj -> new QuantidadeTotalDTO((Long) obj[0], (Double) obj[1]))
                .toList();
    }

    public DescarteDTO salvarViaPonto(Long idPonto, DescarteDTO dto) {
        dto.pontoColetaId = idPonto;
        return salvar(dto);
    }
}

