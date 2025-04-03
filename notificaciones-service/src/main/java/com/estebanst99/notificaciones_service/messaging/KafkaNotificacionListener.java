package com.estebanst99.notificaciones_service.messaging;

import com.estebanst99.notificaciones_service.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaNotificacionListener {

    private final NotificacionService notificacionService;
    private static final String TOPIC_PEDIDOS = "pedidos-topic";
    private static final String TOPIC_PAGOS = "pagos-topic";
    private static final String GROUP_NOTIFICACIONES = "notificaciones-group";
    private static final String SERVICE_PEDIDOS = "pedidos-service";
    private static final String SERVICE_PAGOS = "pagos-service";
    private static final String PEDIDO_RECIBIDO = "📥 Pedido recibido: {}";
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaNotificacionListener.class);

    @KafkaListener(topics = TOPIC_PEDIDOS, groupId = GROUP_NOTIFICACIONES)
    public void escucharDesdePedidos(String mensaje) {
        LOGGER.debug(PEDIDO_RECIBIDO, mensaje);
        notificacionService.guardar(mensaje, SERVICE_PEDIDOS);
    }

    @KafkaListener(topics = TOPIC_PAGOS, groupId = GROUP_NOTIFICACIONES)
    public void escucharDesdePagos(String mensaje) {
        LOGGER.debug(PEDIDO_RECIBIDO, mensaje);
        notificacionService.guardar(mensaje, SERVICE_PAGOS);
    }
}
