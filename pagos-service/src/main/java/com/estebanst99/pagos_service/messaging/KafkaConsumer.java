package com.estebanst99.pagos_service.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final String TOPIC = "pedidos-topic";
    private static final String GROUP_ID = "pagos-group";
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void escucharEventosPedidos(String mensaje) {
      LOGGER.info("📥 Evento recibido desde Kafka: {}" , mensaje);
    }
}
