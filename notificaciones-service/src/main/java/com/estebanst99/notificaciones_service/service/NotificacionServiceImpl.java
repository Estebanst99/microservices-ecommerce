package com.estebanst99.notificaciones_service.service;

import com.estebanst99.notificaciones_service.model.Notificacion;
import com.estebanst99.notificaciones_service.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificacionServiceImpl implements NotificacionService {

    private final NotificacionRepository notificacionRepository;

    @Override
    public Notificacion guardar(String mensaje, String origen) {
        Notificacion notificacion = Notificacion.builder()
                .mensaje(mensaje)
                .origen(origen)
                .timestamp(LocalDateTime.now())
                .build();
        return notificacionRepository.save(notificacion);
    }

    @Override
    public List<Notificacion> obtenerPorOrigen(String origen) {
        return notificacionRepository.findByOrigen(origen);
    }
}
