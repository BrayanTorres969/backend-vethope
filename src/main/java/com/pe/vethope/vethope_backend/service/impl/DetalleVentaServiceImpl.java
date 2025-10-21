package com.pe.vethope.vethope_backend.service.impl;

import com.pe.vethope.vethope_backend.dto.DetalleVentaDTO;
import com.pe.vethope.vethope_backend.entity.DetalleVenta;
import com.pe.vethope.vethope_backend.exception.NotFoundException;
import com.pe.vethope.vethope_backend.mapper.DetalleVentaMapper;
import com.pe.vethope.vethope_backend.repository.DetalleVentaRepository;
import com.pe.vethope.vethope_backend.repository.ProductoRepository;
import com.pe.vethope.vethope_backend.repository.VentaRepository;
import com.pe.vethope.vethope_backend.service.DetalleVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleVentaServiceImpl implements DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;
    private final DetalleVentaMapper detalleVentaMapper;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<DetalleVentaDTO> listarTodos() {
        return detalleVentaMapper.toDTOList(detalleVentaRepository.findByActivoTrue());
    }

    @Override
    public DetalleVentaDTO buscarPorId(Long id) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DetalleVenta con ID " + id + " no encontrado"));
        return detalleVentaMapper.toDTO(detalle);
    }

    @Override
    public DetalleVentaDTO crear(DetalleVentaDTO detalleVentaDTO) {
        DetalleVenta detalle = detalleVentaMapper.toEntity(detalleVentaDTO);

        // Asociar entidades relacionadas (Venta y Producto)
        detalle.setVenta(
                ventaRepository.findById(detalleVentaDTO.getId_venta())
                        .orElseThrow(() -> new NotFoundException("Venta con ID " + detalleVentaDTO.getId_venta() + " no encontrada"))
        );
        detalle.setProducto(
                productoRepository.findById(detalleVentaDTO.getId_producto())
                        .orElseThrow(() -> new NotFoundException("Producto con ID " + detalleVentaDTO.getId_producto() + " no encontrado"))
        );

        detalle.setActivo(true);
        DetalleVenta guardado = detalleVentaRepository.save(detalle);
        return detalleVentaMapper.toDTO(guardado);
    }

    @Override
    public DetalleVentaDTO actualizar(Long id, DetalleVentaDTO detalleVentaDTO) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DetalleVenta con ID " + id + " no encontrado"));

        detalle.setCantidad(detalleVentaDTO.getCantidad());
        detalle.setSubtotal(detalleVentaDTO.getSubtotal());

        DetalleVenta actualizado = detalleVentaRepository.save(detalle);
        return detalleVentaMapper.toDTO(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("DetalleVenta con ID " + id + " no encontrado"));
        detalle.setActivo(false);
        detalleVentaRepository.save(detalle);

    }
}
