package com.pe.vethope.vethope_backend.service.impl;


import com.pe.vethope.vethope_backend.dto.ProductoDTO;
import com.pe.vethope.vethope_backend.entity.Producto;
import com.pe.vethope.vethope_backend.exception.NotFoundException;
import com.pe.vethope.vethope_backend.mapper.ProductoMapper;
import com.pe.vethope.vethope_backend.repository.ProductoRepository;
import com.pe.vethope.vethope_backend.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    @Override
    public List<ProductoDTO> listarTodos() {
        return productoMapper.toDTOList(productoRepository.findByActivoTrue());
    }

    @Override
    public ProductoDTO buscarPorId(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto con ID " + id + " no encontrado"));;
        return productoMapper.toDTO(producto);
    }

    @Override
    public ProductoDTO crear(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        producto.setActivo(true);
        return productoMapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto con ID " + id + " no encontrado"));

        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setStock(productoDTO.getStock());

        return productoMapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public void eliminar(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto con ID " + id + " no encontrado"));
        producto.setActivo(false);
        productoRepository.save(producto);

    }
}
