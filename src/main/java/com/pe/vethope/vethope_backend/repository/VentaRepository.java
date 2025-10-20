package com.pe.vethope.vethope_backend.repository;


import com.pe.vethope.vethope_backend.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByActivoTrue();
}
