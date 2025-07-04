package com.apiweb.pagina.Entidades;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String logo;
    private String mision;
    private String vision;
     @OneToMany(mappedBy = "empresa", cascade =  CascadeType.ALL , orphanRemoval = true)
     @JsonManagedReference
     private List<Banner> banners=new ArrayList<>();
}
