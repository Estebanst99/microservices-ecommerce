package com.estebanst99.pagos_service.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long pedidoId;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private String estado; // Pendiente, Completado, Rechazado
}
