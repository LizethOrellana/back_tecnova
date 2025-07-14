package com.apiweb.pagina.Repositorios;

import com.apiweb.pagina.Entidades.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    List<Marca> findByNombreContainingIgnoreCase(String nombre);
}
