package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Carrito;
import com.apiweb.pagina.Entidades.CarritoProducto;
import com.apiweb.pagina.Entidades.Producto;
import com.apiweb.pagina.Entidades.Usuario;
import com.apiweb.pagina.Repositorios.CarritoRepository;
import com.apiweb.pagina.Repositorios.ProductoRepository;
import com.apiweb.pagina.Repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    // ✅ Obtener todos los carritos
    public List<Carrito> obtenerTodos() {
        return carritoRepository.findAll();
    }

    // Obtener por usuario
    public List<Carrito> obtenerHistorialPorUsuario(Long usuarioId) {
        return carritoRepository.findByUsuarioSecuencialWithProductos(usuarioId);
    }

    public Optional<Carrito> obtenerCarritoActivo(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        return carritoRepository.findCarritoActivo(usuario, "creado");
    }


    // ✅ Obtener un carrito por ID
    public Optional<Carrito> obtenerPorId(Long id) {
        return carritoRepository.findById(id);
    }

    // ✅ Crear un nuevo carrito
    public Carrito crear(Carrito carrito) {
        Long userId = carrito.getUsuario().getSecuencial();
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Optional<Carrito> carritoExistenteOpt = carritoRepository.findCarritoActivo(usuario, "creado");

        if (carritoExistenteOpt.isPresent()) {
            Carrito carritoExistente = carritoExistenteOpt.get();

            // En lugar de mezclar productos aquí, simplemente llamamos a incrementarCantidad por cada uno
            if (carrito.getProductos() != null) {
                for (CarritoProducto nuevo : carrito.getProductos()) {
                    Long productoId = nuevo.getProducto().getId();
                    Integer cantidad = nuevo.getCantidad();
                    this.incrementarCantidad(carritoExistente.getId(), productoId, cantidad);
                }
            }

            // Devolvemos el carrito actualizado (ya se guardó en incrementarCantidad)
            return carritoRepository.findById(carritoExistente.getId()).get();
        } else {
            // No hay carrito activo, entonces crear uno nuevo
            carrito.setUsuario(usuario);
            carrito.setEstado(true);
            carrito.setEstado_proceso("creado");
            carrito.setFechaCreacion(LocalDateTime.now());

            // Vincular los productos nuevos al carrito
            if (carrito.getProductos() == null) {
                carrito.setProductos(new ArrayList<>());
            } else {
                for (CarritoProducto cp : carrito.getProductos()) {
                    cp.setCarrito(carrito);
                }
            }


            return carritoRepository.save(carrito);
        }
    }


    public void incrementarCantidad(Long carritoId, Long productoId, Integer cantidad) {
        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new IllegalArgumentException("Carrito no encontrado"));

        Optional<CarritoProducto> existenteOpt = carrito.getProductos().stream()
                .filter(p -> p.getProducto().getId().equals(productoId))
                .findFirst();

        if (existenteOpt.isPresent()) {
            CarritoProducto existente = existenteOpt.get();
            int cantidadFinal = (cantidad != null && cantidad > 0) ? cantidad : 1;
            existente.setCantidad(existente.getCantidad() + cantidadFinal);
        } else {
            Producto producto = productoRepository.findById(productoId)
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
            int cantidadFinal = (cantidad != null && cantidad > 0) ? cantidad : 1;
            CarritoProducto nuevo = new CarritoProducto();
            nuevo.setProducto(producto);
            nuevo.setCantidad(cantidadFinal); // ← este era el error
            nuevo.setCarrito(carrito);
            carrito.getProductos().add(nuevo);
        }

        carritoRepository.save(carrito);
    }



    // ✅ Actualizar un carrito existente
    public Optional<Carrito> actualizar(Long id, Carrito carritoActualizado) {
        return carritoRepository.findById(id).map(carritoExistente -> {

            // Actualiza datos básicos del carrito si es necesario
            carritoExistente.setEstado(carritoActualizado.getEstado());

            // Sincroniza los productos (CarritoProducto)
            carritoExistente.getProductos().clear(); // Remueve los existentes

            if (carritoActualizado.getProductos() != null) {
                for (CarritoProducto nuevoItem : carritoActualizado.getProductos()) {
                    nuevoItem.setCarrito(carritoExistente); // Asocia el carrito
                    carritoExistente.getProductos().add(nuevoItem);
                }
            }

            return carritoRepository.save(carritoExistente);
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

    public Optional<Carrito> marcarComoPagado(Long carritoId) {
        return carritoRepository.findById(carritoId).map(carrito -> {
            carrito.setEstado_proceso("pagado");
            return carritoRepository.save(carrito);
        });
    }

}
