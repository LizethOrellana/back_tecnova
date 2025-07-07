package com.apiweb.pagina.Controladores;

import com.apiweb.pagina.DTOs.CarritoDto;
import com.apiweb.pagina.Entidades.Carrito;
import com.apiweb.pagina.Servicio.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.apiweb.pagina.Repositorios.CarritoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carritos") // plural por convención REST
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    public CarritoController(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    // ✅ GET todos los carritos
    @GetMapping
    public ResponseEntity<List<Carrito>> getCarritos() {
        List<Carrito> carritos = carritoService.obtenerTodos();
        return ResponseEntity.ok(carritos);
    }

    // ✅ GET un carrito por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrito> getCarritoPorId(@PathVariable Long id) {
        return carritoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ POST crear carrito
    @PostMapping
    public ResponseEntity<Carrito> saveCarrito(@RequestBody Carrito carrito) {
        Carrito saved = carritoService.crear(carrito);
        return ResponseEntity.ok(saved);
    }

    // ✅ PUT actualizar carrito
    @PutMapping("/{id}")
    public ResponseEntity<Carrito> updateCarrito(@PathVariable Long id, @RequestBody Carrito carrito) {
        return carritoService.actualizar(id, carrito)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ DELETE eliminar carrito
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrito(@PathVariable Long id) {
        boolean deleted = carritoService.eliminar(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    private final CarritoRepository carritoRepository;

    @GetMapping("/usuario/{usuarioId}")
    public List<CarritoDto> obtenerHistorialPorUsuario(@PathVariable Long usuarioId) {

        List<Carrito> carritos = carritoRepository.findByUsuarioSecuencialWithProductos(usuarioId);
        return carritos.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private CarritoDto mapToDto(Carrito carrito) {
        CarritoDto dto = new CarritoDto();
        dto.usuarioSecuencial = carrito.getUsuario().getSecuencial();
        dto.fechaCreacion = carrito.getFechaCreacion();

        dto.productos = carrito.getProductos().stream().map(cp -> {
            CarritoDto.ProductoItem item = new CarritoDto.ProductoItem();
            item.productoId = cp.getProducto().getId();
            item.cantidad = cp.getCantidad();
            item.precio = cp.getProducto().getPrecio();
            return item;
        }).collect(Collectors.toList());

        return dto;
    }
}




