package com.estebanst99.notificaciones_service.controller;

import com.estebanst99.notificaciones_service.model.Notificacion;
import com.estebanst99.notificaciones_service.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;
    private static final String ORIGEN_TODOS = "todos";
    private static final String ORIGEN_NOTIFICACION = "origen";

    @GetMapping
    public ResponseEntity<List<Notificacion>> obtenerTodas(
            @RequestParam(value = ORIGEN_NOTIFICACION, required = false) String origen) {

        if (origen != null && !origen.isBlank()) {
            return ResponseEntity.ok(notificacionService.obtenerPorOrigen(origen));
        }
        return ResponseEntity.ok(notificacionService.obtenerPorOrigen(ORIGEN_TODOS));
    }

    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(
            @RequestParam String mensaje,
            @RequestParam String origen) {

        Notificacion notificacion = notificacionService.guardar(mensaje, origen);
        return ResponseEntity.ok(notificacion);
    }
}
