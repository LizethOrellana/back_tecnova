package com.apiweb.pagina.Controladores;

import com.apiweb.pagina.Entidades.Carrito;
import com.apiweb.pagina.Servicio.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos") // plural por convención REST
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

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
}
