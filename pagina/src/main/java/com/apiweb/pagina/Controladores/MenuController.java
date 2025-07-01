package com.apiweb.pagina.Controladores;

import com.apiweb.pagina.Entidades.Menu;
import com.apiweb.pagina.Servicio.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    // GET /api/menus
    @GetMapping
    public ResponseEntity<List<Menu>> listarTodos() {
        return ResponseEntity.ok(menuService.obtenerTodos());
    }

    // GET /api/menus/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Menu> obtenerPorId(@PathVariable Long id) {
        return menuService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/menus
    @PostMapping
    public ResponseEntity<Menu> crear(@RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.crear(menu));
    }

    // PUT /api/menus/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Menu> actualizar(@PathVariable Long id, @RequestBody Menu menu) {
        return menuService.actualizar(id, menu)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/menus/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (menuService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
