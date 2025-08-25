package com.pe.vethope.vethope_backend.service;

import com.pe.vethope.vethope_backend.dto.LoginRequest;
import com.pe.vethope.vethope_backend.dto.LoginResponse;
import com.pe.vethope.vethope_backend.dto.UserDTO;
import com.pe.vethope.vethope_backend.entity.Usuario;
import com.pe.vethope.vethope_backend.mapper.UsuarioMapper;
import com.pe.vethope.vethope_backend.repository.UsuarioRepository;
import com.pe.vethope.vethope_backend.service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsernameAndActivoTrue(loginRequest.getUsername());

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado o inactivo");
        }

        Usuario usuario = usuarioOpt.get();

        if (!passwordEncoder.matches(loginRequest.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        String token = jwtUtil.generateJwtToken(usuario.getUsername());
        UserDTO userDTO = usuarioMapper.toDTO(usuario);

        return LoginResponse.builder()
                .token(token)
                .usuario(userDTO)
                .build();
    }

    public UserDTO registrarUsuario(UserDTO userDTO) {
        if (usuarioRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("El username ya está en uso");
        }

        if (usuarioRepository.existsByCorreo(userDTO.getCorreo())) {
            throw new RuntimeException("El correo ya está en uso");
        }

        Usuario usuario = usuarioMapper.toEntity(userDTO);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setActivo(true);

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuarioGuardado);
    }

    public Optional<UserDTO> findByUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .map(usuarioMapper::toDTO);
    }
}