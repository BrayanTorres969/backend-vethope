package com.pe.vethope.vethope_backend.mapper;

import com.pe.vethope.vethope_backend.dto.ClienteDTO;
import com.pe.vethope.vethope_backend.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(source = "id_cliente", target = "id_cliente")
    ClienteDTO toDTO(Cliente cliente);
    @Mapping(source = "id_cliente", target = "id_cliente")
    Cliente toEntity(ClienteDTO clienteDTO);
    List<ClienteDTO> toDTOList(List<Cliente> clientes);
}
