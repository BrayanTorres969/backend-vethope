package com.pe.vethope.vethope_backend.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaRequestDTO {

    private Long id_tipo_comprobante;
    private Long id_cliente;
    private List<DetalleVentaRequestDTO> detalle;
}
