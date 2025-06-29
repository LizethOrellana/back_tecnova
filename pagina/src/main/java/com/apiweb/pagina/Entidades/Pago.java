package com.apiweb.pagina.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pedido pedido;

    private String metodoPago; // tarjeta, transferencia, efectivo
    private String estadoPago = "pendiente";
    private LocalDateTime fechaPago = LocalDateTime.now();

    // Getters y Setters
}

