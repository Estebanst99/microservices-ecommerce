package com.estebanst99.pedidos_service.controller;

import com.estebanst99.pedidos_service.model.Pedido;
import com.estebanst99.pedidos_service.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping("/")
    public ResponseEntity<Pedido> crearPedido() {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(pedidoService.crearPedido(usuario));
    }

    @GetMapping("/")
    public ResponseEntity<List<Pedido>> obtenerPedidos() {
        String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok(pedidoService.obtenerPedidosUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPedidoPorId(@PathVariable Long id) {
        return pedidoService.obtenerPedidoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
