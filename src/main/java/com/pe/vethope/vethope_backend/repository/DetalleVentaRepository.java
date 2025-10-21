package com.pe.vethope.vethope_backend.repository;

import com.pe.vethope.vethope_backend.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    List<DetalleVenta> findByActivoTrue();
}
