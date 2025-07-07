package com.apiweb.pagina.Controladores;

import com.apiweb.pagina.Entidades.Footer;
import com.apiweb.pagina.Servicio.FooterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/footer")
@CrossOrigin(origins = "*")
public class FooterController {
    @Autowired
    private FooterService footerService;

    // ✅ Listar todos
    @GetMapping
    public List<Footer> obtenerTodos() {
        return footerService.obtenerTodos();
    }

    // ✅ Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Footer> obtenerPorId(@PathVariable Long id) {
        Optional<Footer> footer = footerService.obtenerPorId(id);
        return footer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Crear nuevo
    @PostMapping
    public Footer crear(@RequestBody Footer footer) {
        return footerService.crear(footer);
    }

    // ✅ Actualizar existente
    @PutMapping("/{id}")
    public ResponseEntity<Footer> actualizar(@PathVariable Long id, @RequestBody Footer nuevoFooter) {
        Optional<Footer> actualizado = footerService.actualizar(id, nuevoFooter);
        return actualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (footerService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
