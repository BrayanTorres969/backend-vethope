package com.pe.vethope.vethope_backend.repository;

import com.pe.vethope.vethope_backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByActivoTrue();
}
