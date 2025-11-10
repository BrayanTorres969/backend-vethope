package com.pe.vethope.vethope_backend.mapper;

import com.pe.vethope.vethope_backend.dto.VentaDTO;
import com.pe.vethope.vethope_backend.dto.response.DetalleVentaResponseDTO;
import com.pe.vethope.vethope_backend.dto.response.VentaResponseDTO;
import com.pe.vethope.vethope_backend.entity.DetalleVenta;
import com.pe.vethope.vethope_backend.entity.Venta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VentaMapper {

    /*
    @Mapping(source = "tipoComprobante.id_tipo_comprobante", target = "id_tipo_comprobante")
    @Mapping(source = "cliente.id_cliente", target = "id_cliente")
    VentaDTO toDTO(Venta venta);

    @Mapping(target = "tipoComprobante.id_tipo_comprobante", source = "id_tipo_comprobante")
    @Mapping(target = "cliente.id_cliente", source = "id_cliente")
    @Mapping(target = "activo", constant = "true")
    Venta toEntity(VentaDTO ventaDTO);

    List<VentaDTO> toDTOList(List<Venta> ventas);

     */
    // Para listar o devolver respuesta
    @Mapping(source = "cliente.nombre", target = "cliente")
    @Mapping(source = "tipoComprobante.descripcion", target = "tipo_comprobante")
    @Mapping(source = "detalles", target = "detalle")
    VentaResponseDTO toResponseDTO(Venta venta);

    List<VentaResponseDTO> toResponseList(List<Venta> ventas);

    // Mapear detalle
    @Mapping(source = "producto.nombre", target = "producto")
    DetalleVentaResponseDTO toDetalleResponseDTO(DetalleVenta detalle);
}
