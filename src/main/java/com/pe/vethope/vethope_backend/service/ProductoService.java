package com.pe.vethope.vethope_backend.service;

import com.pe.vethope.vethope_backend.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoDTO> listarTodos();
    ProductoDTO buscarPorId(Long id);
    ProductoDTO crear(ProductoDTO productoDTO);
    ProductoDTO actualizar(Long id,ProductoDTO productoDTO);
    void eliminar(Long id);
}
