package com.apiweb.pagina.Entidades;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;        // Nombre visible del menú
    private String icono;         // Clase del ícono (opcional, ej: "fa fa-home")
    private String ruta;          // URL del router/link
    private boolean activo = true; // Si se muestra o no en el frontend
}
