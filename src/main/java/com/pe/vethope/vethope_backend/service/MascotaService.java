package com.pe.vethope.vethope_backend.service;

import com.pe.vethope.vethope_backend.dto.MascotaDTO;

import java.util.List;

public interface MascotaService {

    MascotaDTO registrarMascota(MascotaDTO mascotaDTO);

    MascotaDTO obtenerMascotaPorId(Long idMascota);

    List<MascotaDTO> listartodos();

    List<MascotaDTO> listarMascotasPorCliente(Long idCliente);

    MascotaDTO actualizarMascota(Long idMascota, MascotaDTO mascotaDTO);

    void eliminarMascota(Long idMascota);
}
