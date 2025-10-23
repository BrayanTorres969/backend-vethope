package com.pe.vethope.vethope_backend.mapper;

import com.pe.vethope.vethope_backend.dto.CitaDTO;
import com.pe.vethope.vethope_backend.entity.Cita;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CitaMapper {

    @Mapping(source = "cliente.id_cliente", target = "id_cliente")
    @Mapping(source = "mascota.id_mascota", target = "id_mascota")
    @Mapping(source = "usuario.id_usuario", target = "id_usuario")
    CitaDTO toDTO(Cita cita);

    @Mapping(target = "cliente.id_cliente", source = "id_cliente")
    @Mapping(target = "mascota.id_mascota", source = "id_mascota")
    @Mapping(target = "usuario.id_usuario", source = "id_usuario")
    Cita toEntity(CitaDTO citaDTO);

    List<CitaDTO> toDTOList(List<Cita> citas);
}
