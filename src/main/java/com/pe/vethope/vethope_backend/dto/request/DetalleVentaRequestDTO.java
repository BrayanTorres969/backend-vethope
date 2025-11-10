package com.pe.vethope.vethope_backend.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaRequestDTO {
    private Long id_producto;
    private Integer cantidad;
}
