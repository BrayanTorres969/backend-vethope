package com.pe.vethope.vethope_backend.mapper;

import com.pe.vethope.vethope_backend.dto.ClienteDTO;
import com.pe.vethope.vethope_backend.entity.Cliente;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteDTO toDTO(Cliente cliente);
    Cliente toEntity(ClienteDTO clienteDTO);
    List<ClienteDTO> toDTOList(List<Cliente> clientes);
}
