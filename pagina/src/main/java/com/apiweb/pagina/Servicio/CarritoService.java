package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Carrito;
import com.apiweb.pagina.Repositorios.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    // ✅ Obtener todos los carritos
    public List<Carrito> obtenerTodos() {
        return carritoRepository.findAll();
    }

    // Obtener por usuario
    public List<Carrito> obtenerHistorialPorUsuario(Long usuarioId) {
        return carritoRepository.findByUsuarioSecuencialWithProductos(usuarioId);
    }



    // ✅ Obtener un carrito por ID
    public Optional<Carrito> obtenerPorId(Long id) {
        return carritoRepository.findById(id);
    }

    // ✅ Crear un nuevo carrito
    public Carrito crear(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    // ✅ Actualizar un carrito existente
    public Optional<Carrito> actualizar(Long id, Carrito carritoActualizado) {
        return carritoRepository.findById(id).map(carrito -> {
            carrito.setUsuario(carritoActualizado.getUsuario());
            carrito.setFechaCreacion(carritoActualizado.getFechaCreacion());
            return carritoRepository.save(carrito);
        });
    }

    // ✅ Eliminar un carrito por ID
    public boolean eliminar(Long id) {
        if (carritoRepository.existsById(id)) {
            carritoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
