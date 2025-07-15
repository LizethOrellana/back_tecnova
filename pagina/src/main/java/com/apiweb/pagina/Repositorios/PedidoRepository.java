package com.apiweb.pagina.Repositorios;

import com.apiweb.pagina.Entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioSecuencial(Long usuarioId);
}
