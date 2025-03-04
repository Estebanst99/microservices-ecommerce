package com.estebanst99.pedidos_service.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void enviarMensaje(String mensaje) {
        kafkaTemplate.send("pedidos-topic", mensaje);
    }
}
