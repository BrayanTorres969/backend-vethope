package com.pe.vethope.vethope_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_comprobante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoComprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_comprobante;

    @Column(nullable = false, unique = true, length = 20)
    private String descripcion; // Ej: BOLETA, FACTURA
}
