package com.apiweb.pagina.Controladores;

import com.apiweb.pagina.Entidades.Pago;
import com.apiweb.pagina.Servicio.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // GET /api/pagos - Listar todos los pagos
    @GetMapping
    public ResponseEntity<List<Pago>> listarTodos() {
        List<Pago> pagos = pagoService.obtenerTodos();
        return ResponseEntity.ok(pagos);
    }

    // GET /api/pagos/{id} - Obtener pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtenerPorId(@PathVariable Long id) {
        return pagoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/pagos - Crear nuevo pago
    @PostMapping
    public ResponseEntity<Pago> crear(@RequestBody Pago pago) {
        Pago creado = pagoService.crear(pago);
        return ResponseEntity.ok(creado);
    }

    // PUT /api/pagos/{id} - Actualizar pago existente
    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(@PathVariable Long id, @RequestBody Pago pago) {
        return pagoService.actualizar(id, pago)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/pagos/{id} - Eliminar pago
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (pagoService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
