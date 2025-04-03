package com.estebanst99.notificaciones_service.service;

import com.estebanst99.notificaciones_service.model.Notificacion;
import java.util.List;

public interface NotificacionService {

    Notificacion guardar(String mensaje, String origen);
    List<Notificacion> obtenerPorOrigen(String origen);
}
