package com.apiweb.pagina.Entidades;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    private Long secuencial;

    private String nombre;
    private String apellido;
    @Column(unique = true, nullable = false)
    private String cedula;
    private String telefono;
    private String username;
    private String password;
    private String pregunta;

    //1 activo
    //0 inactivo
    private int estaActivo;
    
    @ManyToOne
    private TipoUsuario tipoUsuario;
}