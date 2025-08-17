package com.pe.vethope.vethope_backend.service;

import com.pe.vethope.vethope_backend.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteDTO> listarTodos();
    ClienteDTO buscarPorId(Long id);
    ClienteDTO crear(ClienteDTO clienteDTO);
    ClienteDTO actualizar(Long id, ClienteDTO clienteDTO);
    void eliminar(Long id);
}
