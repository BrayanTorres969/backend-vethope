package com.pe.vethope.vethope_backend.service;

import com.pe.vethope.vethope_backend.dto.DetalleVentaDTO;

import java.util.List;

public interface DetalleVentaService {

    List<DetalleVentaDTO> listarTodos();
    DetalleVentaDTO buscarPorId(Long id);
    DetalleVentaDTO crear(DetalleVentaDTO detalleVentaDTO);
    DetalleVentaDTO actualizar(Long id, DetalleVentaDTO detalleVentaDTO);
    void eliminar(Long id);
}
