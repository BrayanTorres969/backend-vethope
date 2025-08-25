package com.pe.vethope.vethope_backend.entity;

import com.pe.vethope.vethope_backend.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class UsuarioDetails implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final String rol;

    public static UsuarioDetails build(UserDTO usuario) {
        return new UsuarioDetails(
                usuario.getId_usuario(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRol()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
