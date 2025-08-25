package com.pe.vethope.vethope_backend.mapper;

import com.pe.vethope.vethope_backend.dto.UserDTO;
import com.pe.vethope.vethope_backend.entity.Usuario;

import java.util.List;

public interface UsuarioMapper {
    UserDTO toDTO(Usuario usuario);
    Usuario toEntity(UserDTO usuarioDto);
    List<UserDTO> toDTOList(List<Usuario> usuarios);
}
