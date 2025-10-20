package com.pe.vethope.vethope_backend.service;

import com.pe.vethope.vethope_backend.dto.VentaDTO;

import java.util.List;

public interface VentaService {

    List<VentaDTO> listarTodos();
    VentaDTO buscarPorId(Long id);
    VentaDTO crear(VentaDTO ventaDTO);
    VentaDTO actualizar(Long id, VentaDTO ventaDTO);
    void eliminar(Long id);
}
