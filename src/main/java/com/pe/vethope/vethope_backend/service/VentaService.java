package com.pe.vethope.vethope_backend.service;

import com.pe.vethope.vethope_backend.dto.VentaDTO;
import com.pe.vethope.vethope_backend.dto.request.VentaRequestDTO;
import com.pe.vethope.vethope_backend.dto.response.VentaResponseDTO;

import java.util.List;

public interface VentaService {

    List<VentaResponseDTO> listarTodos();
    VentaResponseDTO buscarPorId(Long id);
    VentaResponseDTO crear(VentaRequestDTO ventaRequestDTO);
    VentaResponseDTO actualizar(Long id, VentaRequestDTO ventaRequestDTO);
    void eliminar(Long id);
}
