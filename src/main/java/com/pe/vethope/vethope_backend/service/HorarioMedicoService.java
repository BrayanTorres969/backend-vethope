package com.pe.vethope.vethope_backend.service;

import com.pe.vethope.vethope_backend.dto.HorarioMedicoDTO;

import java.util.List;

public interface HorarioMedicoService {

    List<HorarioMedicoDTO> listarTodos();
    HorarioMedicoDTO buscarPorId(Long id);
    List<HorarioMedicoDTO> listarPorMedico(Long idUsuario);
    HorarioMedicoDTO crear(HorarioMedicoDTO horarioDTO);
    HorarioMedicoDTO actualizar(Long id, HorarioMedicoDTO horarioDTO);
    void eliminar(Long id);
}
