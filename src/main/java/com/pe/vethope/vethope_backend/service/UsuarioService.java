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

import java.util.List;
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

        String token = jwtUtil.generateJwtToken(usuario);
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

    public List<UserDTO> listarUsuarios() {
        List<Usuario> usuariosActivos = usuarioRepository.findAllByActivoTrue();
        return usuariosActivos.stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    public List<UserDTO> listarMedicos() {
        List<Usuario> doctores = usuarioRepository.findAllMedicosActivos();
        return doctores.stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    public UserDTO editarUsuario(Long id, UserDTO userDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Validar si el username o correo ya existen en otro usuario
        if (!usuario.getUsername().equals(userDTO.getUsername()) &&
                usuarioRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("El username ya está en uso");
        }

        if (!usuario.getCorreo().equals(userDTO.getCorreo()) &&
                usuarioRepository.existsByCorreo(userDTO.getCorreo())) {
            throw new RuntimeException("El correo ya está en uso");
        }

        // Actualizar datos permitidos
        usuario.setUsername(userDTO.getUsername());
        usuario.setNombre(userDTO.getNombre());
        usuario.setApellido(userDTO.getApellido());
        usuario.setCorreo(userDTO.getCorreo());
        usuario.setDni(userDTO.getDni());
        usuario.setTelefono(userDTO.getTelefono());
        usuario.setRol(Enum.valueOf(com.pe.vethope.vethope_backend.enums.Rol.class, userDTO.getRol()));

        // Si viene un nuevo password, actualizarlo
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuarioActualizado);
    }

    public void eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.getActivo()) {
            throw new RuntimeException("El usuario ya se encuentra inactivo");
        }

        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    public List<UserDTO> listarUsuariosInactivos() {
        List<Usuario> usuariosInactivos = usuarioRepository.findAllByActivoFalse();
        return usuariosInactivos.stream()
                .map(usuarioMapper::toDTO)
                .toList();
    }

    public UserDTO activarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (usuario.getActivo()) {
            throw new RuntimeException("El usuario ya se encuentra activo");
        }

        usuario.setActivo(true);
        Usuario usuarioActivado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(usuarioActivado);
    }
}