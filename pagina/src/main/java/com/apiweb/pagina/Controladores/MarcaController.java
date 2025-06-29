package com.apiweb.pagina.Controladores;

import com.apiweb.pagina.Entidades.Marca;
import com.apiweb.pagina.Servicio.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    // GET /api/marcas - Obtener todas las marcas
    @GetMapping
    public ResponseEntity<List<Marca>> listarTodas() {
        List<Marca> marcas = marcaService.obtenerTodas();
        return ResponseEntity.ok(marcas);
    }

    // GET /api/marcas/{id} - Obtener marca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Marca> obtenerPorId(@PathVariable Long id) {
        return marcaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/marcas - Crear una marca
    @PostMapping
    public ResponseEntity<Marca> crear(@RequestBody Marca marca) {
        Marca creada = marcaService.crear(marca);
        return ResponseEntity.ok(creada);
    }

    // PUT /api/marcas/{id} - Actualizar una marca
    @PutMapping("/{id}")
    public ResponseEntity<Marca> actualizar(@PathVariable Long id, @RequestBody Marca marca) {
        return marcaService.actualizar(id, marca)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/marcas/{id} - Eliminar una marca
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (marcaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
