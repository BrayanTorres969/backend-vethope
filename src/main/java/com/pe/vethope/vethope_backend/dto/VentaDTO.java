package com.pe.vethope.vethope_backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {
    private Long id_tipo_comprobante;
    private Long id_cliente;
    private Double total;
    private LocalDateTime fecha;
}
