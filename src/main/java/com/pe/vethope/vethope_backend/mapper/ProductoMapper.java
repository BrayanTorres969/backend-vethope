package com.pe.vethope.vethope_backend.mapper;

import com.pe.vethope.vethope_backend.dto.ProductoDTO;
import com.pe.vethope.vethope_backend.entity.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    @Mapping(source = "id_producto", target = "id_producto")
    ProductoDTO toDTO(Producto producto);
    @Mapping(source = "id_producto", target = "id_producto")
    Producto toEntity(ProductoDTO productoDTO);
    List<ProductoDTO> toDTOList(List<Producto> productos);
}
