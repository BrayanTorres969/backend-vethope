package com.pe.vethope.vethope_backend.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaResponseDTO {

    private String producto;
    private Integer cantidad;
    private Double subtotal;
}
