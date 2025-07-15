package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Producto;
import com.apiweb.pagina.Repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // ✅ Obtener todos los productos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    // ✅ Obtener un producto por ID
    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    // ✅ Crear nuevo producto
    public Producto crear(Producto producto) {
        return productoRepository.save(producto);
    }

    // ✅ Actualizar un producto existente
    public Optional<Producto> actualizar(Long id, Producto nuevoProducto) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(nuevoProducto.getNombre());
            producto.setDescripcion(nuevoProducto.getDescripcion());
            producto.setPrecio(nuevoProducto.getPrecio());
            producto.setStock(nuevoProducto.getStock());
            producto.setImagenUrl(nuevoProducto.getImagenUrl());
            producto.setCategoria(nuevoProducto.getCategoria());
            producto.setMarca(nuevoProducto.getMarca());
            return productoRepository.save(producto);
        });
    }

    // ✅ Eliminar un producto por ID
    public boolean eliminar(Long id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public Optional<Producto> disminuirStock(Long id, int cantidad) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        if (productoOpt.isEmpty()) {
            return Optional.empty();
        }

        Producto producto = productoOpt.get();

        if (producto.getStock() < cantidad) {
            return Optional.empty();
        }

        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto); // Aquí guardas el cambio
        return Optional.of(producto);
    }


}
