package com.pe.vethope.vethope_backend.repository;

import com.pe.vethope.vethope_backend.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
    List<DetalleVenta> findByActivoTrue();

    @Query("SELECT d FROM DetalleVenta d WHERE d.venta.id_venta = :idVenta")
    List<DetalleVenta> findByVentaId(@Param("idVenta") Long idVenta);
}
