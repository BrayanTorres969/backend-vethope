package com.pe.vethope.vethope_backend.service;

import com.pe.vethope.vethope_backend.dto.CitaDTO;

import java.util.List;

public interface CitaService {
    List<CitaDTO> listarTodos();
    CitaDTO buscarPorId(Long id);
    CitaDTO crear(CitaDTO citaDTO);
    CitaDTO actualizar(Long id, CitaDTO citaDTO);
    void eliminar(Long id);
}
