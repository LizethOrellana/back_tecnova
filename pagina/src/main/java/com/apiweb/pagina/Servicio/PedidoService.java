package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Pedido;
import com.apiweb.pagina.Repositorios.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> obtenerPedidosPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuarioSecuencial(usuarioId);
    }


    // ✅ Obtener todos los pedidos
    public List<Pedido> obtenerTodos() {
        return pedidoRepository.findAll();
    }

    // ✅ Obtener un pedido por ID
    public Optional<Pedido> obtenerPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // ✅ Crear nuevo pedido
    public Pedido crear(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    // ✅ Actualizar un pedido existente
    public Optional<Pedido> actualizar(Long id, Pedido nuevoPedido) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setUsuario(nuevoPedido.getUsuario());
            pedido.setTotal(nuevoPedido.getTotal());
            pedido.setEstado(nuevoPedido.getEstado());
            pedido.setFechaPedido(nuevoPedido.getFechaPedido());
            return pedidoRepository.save(pedido);
        });
    }

    // ✅ Eliminar un pedido por ID
    public boolean eliminar(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
