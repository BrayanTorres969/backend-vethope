package com.pe.vethope.vethope_backend.mapper;

import com.pe.vethope.vethope_backend.dto.ProductoDTO;
import com.pe.vethope.vethope_backend.entity.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    ProductoDTO toDTO(Producto producto);
    Producto toEntity(ProductoDTO productoDTO);
    List<ProductoDTO> toDTOList(List<Producto> productos);
}
