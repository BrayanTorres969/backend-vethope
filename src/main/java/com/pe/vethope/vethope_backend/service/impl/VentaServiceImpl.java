package com.pe.vethope.vethope_backend.service.impl;

import com.pe.vethope.vethope_backend.dto.VentaDTO;
import com.pe.vethope.vethope_backend.dto.request.DetalleVentaRequestDTO;
import com.pe.vethope.vethope_backend.dto.request.VentaRequestDTO;
import com.pe.vethope.vethope_backend.dto.response.VentaResponseDTO;
import com.pe.vethope.vethope_backend.entity.*;
import com.pe.vethope.vethope_backend.exception.BusinessException;
import com.pe.vethope.vethope_backend.exception.NotFoundException;
import com.pe.vethope.vethope_backend.mapper.VentaMapper;
import com.pe.vethope.vethope_backend.repository.*;
import com.pe.vethope.vethope_backend.service.VentaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepository productoRepository;
    private final VentaMapper ventaMapper;
    private final ClienteRepository clienteRepository;
    private final TipoComprobanteRepository tipoComprobanteRepository;


    @Override
    public List<VentaResponseDTO> listarTodos() {
        List<Venta> ventas = ventaRepository.findByActivoTrue();
        return ventaMapper.toResponseList(ventas);
    }

    @Override
    public VentaResponseDTO buscarPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta con ID " + id + " no encontrada"));
        return ventaMapper.toResponseDTO(venta);
    }

    @Transactional
    @Override
    public VentaResponseDTO crear(VentaRequestDTO ventaRequest) {

        // 1 Buscar cliente y tipo comprobante
        Cliente cliente = clienteRepository.findById(ventaRequest.getId_cliente())
                .orElseThrow(() -> new NotFoundException("Cliente con ID " + ventaRequest.getId_cliente() + " no encontrado"));

        TipoComprobante tipoComprobante = tipoComprobanteRepository.findById(ventaRequest.getId_tipo_comprobante())
                .orElseThrow(() -> new NotFoundException("Tipo de comprobante con ID " + ventaRequest.getId_tipo_comprobante() + " no encontrado"));
        // 2 Crear venta base
        Venta venta = Venta.builder()
                .cliente(cliente)
                .tipoComprobante(tipoComprobante)
                .fecha(LocalDateTime.now())
                .activo(true)
                .build();

        double total = 0.0;
        List<DetalleVenta> detalles = new ArrayList<>();
        // 3 Procesar detalle
        for (DetalleVentaRequestDTO item : ventaRequest.getDetalle()) {
            Producto producto = productoRepository.findById(item.getId_producto())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + item.getId_producto()));

            if (producto.getStock() < item.getCantidad()) {
                throw new BusinessException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            double subtotal = producto.getPrecio() * item.getCantidad();
            total += subtotal;

            DetalleVenta detalle = DetalleVenta.builder()
                    .producto(producto)
                    .cantidad(item.getCantidad())
                    .subtotal(subtotal)
                    .venta(venta)
                    .activo(true)
                    .build();

            detalles.add(detalle);

            // Descontar stock
            producto.setStock(producto.getStock() - item.getCantidad());
            productoRepository.save(producto);
        }
        // 4 Guardar venta y detalles
        venta.setTotal(total);
        ventaRepository.save(venta);
        detalleVentaRepository.saveAll(detalles);
        venta.setDetalles(detalles);

        venta.setActivo(true);
        // 5 Devolver DTO de respuesta
        return ventaMapper.toResponseDTO(venta);
    }

    @Transactional
    @Override
    public VentaResponseDTO actualizar(Long idVenta, VentaRequestDTO ventaRequest) {
        // 1. Buscar venta existente
        Venta venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new NotFoundException("Venta con ID " + idVenta + " no encontrada"));

        // 2. Buscar cliente y tipo comprobante
        Cliente cliente = clienteRepository.findById(ventaRequest.getId_cliente())
                .orElseThrow(() -> new NotFoundException("Cliente con ID " + ventaRequest.getId_cliente() + " no encontrado"));

        TipoComprobante tipoComprobante = tipoComprobanteRepository.findById(ventaRequest.getId_tipo_comprobante())
                .orElseThrow(() -> new NotFoundException("Tipo de comprobante con ID " + ventaRequest.getId_tipo_comprobante() + " no encontrado"));

        venta.setCliente(cliente);
        venta.setTipoComprobante(tipoComprobante);

        // 3. Obtener detalles existentes
        List<DetalleVenta> detallesExistentes = detalleVentaRepository.findByVentaId(venta.getId_venta());
        Map<Long, DetalleVenta> mapaDetallesExistentes = detallesExistentes.stream()
                .collect(Collectors.toMap(d -> d.getProducto().getId_producto(), d -> d));

        double total = 0.0;
        List<DetalleVenta> detallesParaGuardar = new ArrayList<>();

        for (DetalleVentaRequestDTO item : ventaRequest.getDetalle()) {
            Producto producto = productoRepository.findById(item.getId_producto())
                    .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + item.getId_producto()));

            DetalleVenta detalleExistente = mapaDetallesExistentes.get(producto.getId_producto());

            if (detalleExistente != null && detalleExistente.getActivo()) {
                // Ajustar stock según diferencia de cantidad
                int diferencia = item.getCantidad() - detalleExistente.getCantidad();
                if (producto.getStock() < diferencia) {
                    throw new BusinessException("Stock insuficiente para el producto: " + producto.getNombre());
                }
                producto.setStock(producto.getStock() - diferencia);
                productoRepository.save(producto);

                // Actualizar detalle
                detalleExistente.setCantidad(item.getCantidad());
                detalleExistente.setSubtotal(producto.getPrecio() * item.getCantidad());
                detallesParaGuardar.add(detalleExistente);

                // Acumulamos total
                total += detalleExistente.getSubtotal();

                // Quitar del mapa para saber qué detalles no se actualizaron
                mapaDetallesExistentes.remove(producto.getId_producto());
            } else {
                // Nuevo detalle
                if (producto.getStock() < item.getCantidad()) {
                    throw new BusinessException("Stock insuficiente para el producto: " + producto.getNombre());
                }
                producto.setStock(producto.getStock() - item.getCantidad());
                productoRepository.save(producto);

                DetalleVenta nuevoDetalle = DetalleVenta.builder()
                        .producto(producto)
                        .cantidad(item.getCantidad())
                        .subtotal(producto.getPrecio() * item.getCantidad())
                        .venta(venta)
                        .activo(true)
                        .build();
                detallesParaGuardar.add(nuevoDetalle);

                total += nuevoDetalle.getSubtotal();
            }
        }

        // 4. Desactivar los detalles que no están en la nueva venta
        for (DetalleVenta detalleRestante : mapaDetallesExistentes.values()) {
            if (detalleRestante.getActivo()) {
                detalleRestante.setActivo(false);
                // Devolver stock
                Producto producto = detalleRestante.getProducto();
                producto.setStock(producto.getStock() + detalleRestante.getCantidad());
                productoRepository.save(producto);
                detallesParaGuardar.add(detalleRestante);
            }
        }

        venta.setTotal(total);
        ventaRepository.save(venta);
        detalleVentaRepository.saveAll(detallesParaGuardar);

        return ventaMapper.toResponseDTO(venta);
    }


    @Override
    public void eliminar(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta con ID " + id + " no encontrada"));

        venta.setActivo(false);
        ventaRepository.save(venta);
    }
}
