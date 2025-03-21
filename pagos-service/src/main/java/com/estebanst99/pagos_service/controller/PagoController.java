package com.estebanst99.pagos_service.controller;

import com.estebanst99.pagos_service.model.Pago;
import com.estebanst99.pagos_service.service.PagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping("/")
    public ResponseEntity<Pago> crearPago(@RequestParam Long pedidoId) {
        try {
            String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
            return ResponseEntity.ok(pagoService.procesarPago(pedidoId, usuario));
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}