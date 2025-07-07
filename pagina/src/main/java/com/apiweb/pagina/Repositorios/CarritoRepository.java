package com.apiweb.pagina.Repositorios;

import com.apiweb.pagina.Entidades.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    @Query("""
    SELECT DISTINCT c FROM Carrito c
    LEFT JOIN FETCH c.productos cp
    LEFT JOIN FETCH cp.producto
    WHERE c.usuario.secuencial = :usuarioId
""")
    List<Carrito> findByUsuarioSecuencialWithProductos(@Param("usuarioId") Long usuarioId);
}

