package com.pe.vethope.vethope_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {

    private Long id_detalle;
    private Long id_venta;
    private Long id_producto;
    private Integer cantidad;
    private Double subtotal;
}
