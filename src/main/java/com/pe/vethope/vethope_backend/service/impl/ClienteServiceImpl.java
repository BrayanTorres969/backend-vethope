package com.pe.vethope.vethope_backend.service.impl;

import com.pe.vethope.vethope_backend.dto.ClienteDTO;
import com.pe.vethope.vethope_backend.entity.Cliente;
import com.pe.vethope.vethope_backend.mapper.ClienteMapper;
import com.pe.vethope.vethope_backend.repository.ClienteRepository;
import com.pe.vethope.vethope_backend.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;


    @Override
    public List<ClienteDTO> listarTodos() {
        return clienteMapper.toDTOList(clienteRepository.findAll());
    }

    @Override
    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return clienteMapper.toDTO(cliente);
    }

    @Override
    public ClienteDTO crear(ClienteDTO clienteDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteDTO);
        return clienteMapper.toDTO(clienteRepository.save(cliente));
    }

    @Override
    public ClienteDTO actualizar(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setTelefono(clienteDTO.getTelefono());

        return clienteMapper.toDTO(clienteRepository.save(cliente));
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
