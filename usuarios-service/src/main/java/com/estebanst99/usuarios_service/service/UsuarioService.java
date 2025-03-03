package com.estebanst99.usuarios_service.service;

import com.estebanst99.usuarios_service.model.Usuario;
import com.estebanst99.usuarios_service.repository.UsuarioRepository;
import com.estebanst99.usuarios_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String registrarUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
        return jwtUtil.generarToken(usuario.getUsername());
    }

    public String autenticarUsuario(String username, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);

        if (usuario.isPresent() && passwordEncoder.matches(password, usuario.get().getPassword())) {
            return jwtUtil.generarToken(usuario.get().getUsername());
        }
        throw new RuntimeException("Credenciales inválidas");
    }

    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
