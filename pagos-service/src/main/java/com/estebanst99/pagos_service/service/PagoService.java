package com.estebanst99.pagos_service.service;

import com.estebanst99.pagos_service.model.Pago;
import com.estebanst99.pagos_service.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository pagoRepository;

    public Pago procesarPago(Long pedidoId, String usuario) {
        Pago pago = new Pago();
        pago.setPedidoId(pedidoId);
        pago.setUsuario(usuario);
        pago.setEstado("Completado");
        return pagoRepository.save(pago);
    }
}