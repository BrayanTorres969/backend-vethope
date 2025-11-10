package com.pe.vethope.vethope_backend.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaResponseDTO {

    private Long id_venta;
    private String cliente;
    private String tipo_comprobante;
    private Double total;
    private LocalDateTime fecha;
    private List<DetalleVentaResponseDTO> detalle;
}
