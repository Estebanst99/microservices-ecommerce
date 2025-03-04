package com.estebanst99.pedidos_service.service;

import com.estebanst99.pedidos_service.messaging.KafkaProducer;
import com.estebanst99.pedidos_service.model.Pedido;
import com.estebanst99.pedidos_service.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final KafkaProducer kafkaProducer;
    private static final String ESTADO_PENDIENTE = "Pendiente";

    public Pedido crearPedido(String usuario) {
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEstado(ESTADO_PENDIENTE);
        Pedido nuevoPedido = pedidoRepository.save(pedido);

        // Publicar evento en Kafka
        kafkaProducer.enviarMensaje("Nuevo pedido creado: ID " + nuevoPedido.getId() + "/ Usuario: " + usuario);

        return nuevoPedido;
    }

    public List<Pedido> obtenerPedidosUsuario(String usuario) {
        return pedidoRepository.findByUsuario(usuario);
    }

    public Optional<Pedido> obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }
}
