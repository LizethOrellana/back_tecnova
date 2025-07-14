package com.apiweb.pagina.Repositorios;

import com.apiweb.pagina.Entidades.Carrito;
import com.apiweb.pagina.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    @Query("""
    SELECT DISTINCT c FROM Carrito c
    LEFT JOIN FETCH c.productos cp
    LEFT JOIN FETCH cp.producto
    WHERE c.usuario.secuencial = :usuarioId
""")
    List<Carrito> findByUsuarioSecuencialWithProductos(@Param("usuarioId") Long usuarioId);

    @Query("""
    SELECT c FROM Carrito c 
    WHERE c.usuario = :usuario AND c.estado = true AND c.estado_proceso = :estadoProceso
""")
    Optional<Carrito> findCarritoActivo(@Param("usuario") Usuario usuario, @Param("estadoProceso") String estadoProceso);

}

