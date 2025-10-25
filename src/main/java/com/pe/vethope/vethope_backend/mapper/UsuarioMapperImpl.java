package com.pe.vethope.vethope_backend.mapper;

import com.pe.vethope.vethope_backend.dto.UserDTO;
import com.pe.vethope.vethope_backend.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UserDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        return UserDTO.builder()
                .id_usuario(usuario.getId_usuario())
                .username(usuario.getUsername())
                .nombre(usuario.getNombre())
                .apellido(usuario.getApellido())
                .dni(usuario.getDni())
                .correo(usuario.getCorreo())
                .telefono(usuario.getTelefono())
                .rol(usuario.getRol())
                // No incluimos password en el DTO por seguridad
                .build();
    }

    @Override
    public Usuario toEntity(UserDTO userDTO) {
        if (userDTO == null) return null;

        return Usuario.builder()
                .id_usuario(userDTO.getId_usuario())
                .username(userDTO.getUsername())
                .nombre(userDTO.getNombre())
                .apellido(userDTO.getApellido())
                .dni(userDTO.getDni())
                .correo(userDTO.getCorreo())
                .telefono(userDTO.getTelefono())
                .password(userDTO.getPassword())
                .rol(userDTO.getRol())
                .activo(true)
                .build();
    }

    @Override
    public List<UserDTO> toDTOList(List<Usuario> usuarios) {
        if (usuarios == null) return null;

        return usuarios.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}