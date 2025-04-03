package com.estebanst99.notificaciones_service.repository;

import com.estebanst99.notificaciones_service.model.Notificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface NotificacionRepository extends MongoRepository<Notificacion, String> {

    List<Notificacion> findByOrigen(String origen);
}
