package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Pago;
import com.apiweb.pagina.Repositorios.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    // ✅ Obtener todos los pagos
    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    // ✅ Obtener un pago por ID
    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    // ✅ Crear nuevo pago
    public Pago crear(Pago pago) {
        return pagoRepository.save(pago);
    }

    // ✅ Actualizar un pago existente
    public Optional<Pago> actualizar(Long id, Pago nuevoPago) {
        return pagoRepository.findById(id).map(pago -> {
            pago.setPedido(nuevoPago.getPedido());
            pago.setMetodoPago(nuevoPago.getMetodoPago());
            pago.setEstadoPago(nuevoPago.getEstadoPago());
            pago.setFechaPago(nuevoPago.getFechaPago());
            return pagoRepository.save(pago);
        });
    }

    // ✅ Eliminar un pago por ID
    public boolean eliminar(Long id) {
        if (pagoRepository.existsById(id)) {
            pagoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
