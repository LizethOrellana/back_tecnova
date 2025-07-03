package com.apiweb.pagina.Controladores;

import com.apiweb.pagina.Entidades.TipoUsuario;
import com.apiweb.pagina.Servicio.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-usuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    // GET /api/marcas - Obtener todas las marcas
    @GetMapping
    public ResponseEntity<List<TipoUsuario>> listarTodas() {
        List<TipoUsuario> tipoUsuarios = tipoUsuarioService.obtenerTodos();
        return ResponseEntity.ok(tipoUsuarios);
    }

    // GET /api/marcas/{id} - Obtener marca por ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> obtenerPorId(@PathVariable Long id) {
        return tipoUsuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/marcas - Crear una marca
    @PostMapping
    public ResponseEntity<TipoUsuario> crear(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario creada = tipoUsuarioService.crear(tipoUsuario);
        return ResponseEntity.ok(creada);
    }

    // PUT /api/marcas/{id} - Actualizar una marca
    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> actualizar(@PathVariable Long id, @RequestBody TipoUsuario tipoUsuario) {
        return tipoUsuarioService.actualizar(id, tipoUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/marcas/{id} - Eliminar una marca
    @DeleteMapping("/{id}")
    public ResponseEntity<TipoUsuario> eliminar(@PathVariable Long id) {
        return tipoUsuarioService.eliminar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
