package com.apiweb.pagina.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "carrito_productos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarritoProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Carrito carrito;

    @ManyToOne
    private Producto producto;

    private Integer cantidad;
}


