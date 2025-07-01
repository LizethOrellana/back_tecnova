package com.apiweb.pagina.Controladores;

import com.apiweb.pagina.Entidades.Empresa;
import com.apiweb.pagina.Servicio.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/empresa")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // permite solicitudes desde el frontend
public class EmpresaController {

    private final EmpresaService empresaService;

    // GET empresa
    @GetMapping
    public ResponseEntity<Empresa> getEmpresa() {
        Optional<Empresa> empresa = empresaService.findFirst();
        return empresa.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST/PUT empresa con banners
    @PostMapping
    public ResponseEntity<Empresa> saveEmpresa(@RequestBody Empresa empresa) {
        Empresa saved = empresaService.saveFullEmpresa(empresa);
        return ResponseEntity.ok(saved);
    }

    // DELETE empresa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpresa(@PathVariable Long id) {
        boolean deleted = empresaService.deleteById(id);
        return deleted
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
