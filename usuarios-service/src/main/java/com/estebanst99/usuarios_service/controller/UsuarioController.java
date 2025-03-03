package com.estebanst99.usuarios_service.controller;

import com.estebanst99.usuarios_service.model.Usuario;
import com.estebanst99.usuarios_service.security.JwtUtil;
import com.estebanst99.usuarios_service.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;

    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody @Valid Usuario usuario) {
        return ResponseEntity.ok(usuarioService.registrarUsuario(usuario));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid Usuario usuario) {
        return ResponseEntity.ok(usuarioService.autenticarUsuario(usuario.getUsername(), usuario.getPassword()));
    }

    @GetMapping("/perfil")
    public ResponseEntity<String> obtenerPerfilUsuario() {
        // Obtener el usuario autenticado del contexto de seguridad
        User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok("Bienvenido, " + usuario.getUsername());
    }

    @GetMapping("/validar-token")
    public ResponseEntity<Boolean> validarToken(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        boolean esValido = jwtUtil.extraerUsername(token) != null;
        return ResponseEntity.ok(esValido);
    }

    @GetMapping("/info")
    public ResponseEntity<Usuario> obtenerInfoUsuario() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario usuario = usuarioService.obtenerUsuarioPorUsername(username);
        return ResponseEntity.ok(usuario);
    }

}
