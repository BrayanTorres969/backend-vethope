package com.pe.vethope.vethope_backend.config;

import com.pe.vethope.vethope_backend.entity.UsuarioDetails;
import com.pe.vethope.vethope_backend.service.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String jwt = authHeader.substring(7);
            if (jwtUtil.validateJwtToken(jwt)) {
                String username = jwtUtil.getUsernameFromJwtToken(jwt);
                String rol = jwtUtil.getRolFromJwtToken(jwt);

                var authorities = List.of(new SimpleGrantedAuthority(rol));
                System.out.println("JWT válido - Usuario: " + username + ", Rol: " + rol);

                UsuarioDetails usuarioDetails = new UsuarioDetails(null, username, "", rol);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                usuarioDetails,
                                null,
                                authorities
                        );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Aquí se inyecta el usuario autenticado en el contexto
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                System.out.println("JWT inválido");
            }
        }

        filterChain.doFilter(request, response);
    }
}
