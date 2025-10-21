package com.pe.vethope.vethope_backend.mapper;


import com.pe.vethope.vethope_backend.dto.DetalleVentaDTO;
import com.pe.vethope.vethope_backend.entity.DetalleVenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DetalleVentaMapper {

    @Mapping(source = "venta.id_venta", target = "id_venta")
    @Mapping(source = "producto.id_producto", target = "id_producto")
    DetalleVentaDTO toDTO(DetalleVenta detalleVenta);

    @Mapping(target = "venta.id_venta", source = "id_venta")
    @Mapping(target = "producto.id_producto", source = "id_producto")
    @Mapping(target = "activo", constant = "true")
    DetalleVenta toEntity(DetalleVentaDTO detalleVentaDTO);

    List<DetalleVentaDTO> toDTOList(List<DetalleVenta> detalles);


}
