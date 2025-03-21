package com.estebanst99.pagos_service.repository;

import com.estebanst99.pagos_service.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
