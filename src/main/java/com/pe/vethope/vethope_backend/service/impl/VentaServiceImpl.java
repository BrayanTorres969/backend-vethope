package com.pe.vethope.vethope_backend.service.impl;

import com.pe.vethope.vethope_backend.dto.VentaDTO;
import com.pe.vethope.vethope_backend.entity.Cliente;
import com.pe.vethope.vethope_backend.entity.TipoComprobante;
import com.pe.vethope.vethope_backend.entity.Venta;
import com.pe.vethope.vethope_backend.exception.NotFoundException;
import com.pe.vethope.vethope_backend.mapper.VentaMapper;
import com.pe.vethope.vethope_backend.repository.ClienteRepository;
import com.pe.vethope.vethope_backend.repository.TipoComprobanteRepository;
import com.pe.vethope.vethope_backend.repository.VentaRepository;
import com.pe.vethope.vethope_backend.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;
    private final ClienteRepository clienteRepository;
    private final TipoComprobanteRepository tipoComprobanteRepository;


    @Override
    public List<VentaDTO> listarTodos() {
        return ventaMapper.toDTOList(ventaRepository.findByActivoTrue());
    }

    @Override
    public VentaDTO buscarPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta con ID " + id + " no encontrada"));
        return ventaMapper.toDTO(venta);
    }

    @Override
    public VentaDTO crear(VentaDTO ventaDTO) {
        // Convertimos DTO a entidad
        Venta venta = ventaMapper.toEntity(ventaDTO);

        // Obtenemos Cliente y TipoComprobante
        Cliente cliente = clienteRepository.findById(ventaDTO.getId_cliente())
                .orElseThrow(() -> new NotFoundException("Cliente con ID " + ventaDTO.getId_cliente() + " no encontrado"));

        TipoComprobante tipoComprobante = tipoComprobanteRepository.findById(ventaDTO.getId_tipo_comprobante())
                .orElseThrow(() -> new NotFoundException("Tipo de comprobante con ID " + ventaDTO.getId_tipo_comprobante() + " no encontrado"));

        // Asignamos las relaciones
        venta.setCliente(cliente);
        venta.setTipoComprobante(tipoComprobante);

        // Seteamos campos adicionales
        venta.setFecha(LocalDateTime.now());
        venta.setActivo(true);

        // Guardamos la venta
        Venta ventaGuardada = ventaRepository.save(venta);

        // Retornamos DTO
        return ventaMapper.toDTO(ventaGuardada);
    }

    @Override
    public VentaDTO actualizar(Long id, VentaDTO ventaDTO) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta con ID " + id + " no encontrada"));

        // Actualizamos campos básicos
        venta.setTotal(ventaDTO.getTotal());
        venta.setFecha(ventaDTO.getFecha());

        // Actualizamos cliente y comprobante si cambian
        if (ventaDTO.getId_cliente() != null) {
            Cliente cliente = clienteRepository.findById(ventaDTO.getId_cliente())
                    .orElseThrow(() -> new NotFoundException("Cliente con ID " + ventaDTO.getId_cliente() + " no encontrado"));
            venta.setCliente(cliente);
        }

        if (ventaDTO.getId_tipo_comprobante() != null) {
            TipoComprobante tipoComprobante = tipoComprobanteRepository.findById(ventaDTO.getId_tipo_comprobante())
                    .orElseThrow(() -> new NotFoundException("Tipo de comprobante con ID " + ventaDTO.getId_tipo_comprobante() + " no encontrado"));
            venta.setTipoComprobante(tipoComprobante);
        }

        return ventaMapper.toDTO(ventaRepository.save(venta));
    }

    @Override
    public void eliminar(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Venta con ID " + id + " no encontrada"));

        venta.setActivo(false);
        ventaRepository.save(venta);
    }
}
