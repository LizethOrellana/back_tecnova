package com.apiweb.pagina.Controladores;

import com.apiweb.pagina.Entidades.Banner;
import com.apiweb.pagina.Servicio.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    // ✅ Obtener todos los banners de una empresa por ID
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Banner>> obtenerBannersPorEmpresa(@PathVariable Long empresaId) {
        List<Banner> banners = bannerService.obtenerBannersPorEmpresa(empresaId);
        return ResponseEntity.ok(banners);
    }

    // ✅ Obtener un banner por ID
    @GetMapping("/{id}")
    public ResponseEntity<Banner> obtenerPorId(@PathVariable Long id) {
        Optional<Banner> banner = bannerService.obtenerPorId(id);
        return banner.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Crear un nuevo banner
    @PostMapping
    public ResponseEntity<Banner> crear(@RequestBody Banner banner) {
        Banner nuevo = bannerService.crear(banner);
        return ResponseEntity.ok(nuevo);
    }

    // ✅ Actualizar un banner
    @PutMapping("/{id}")
    public ResponseEntity<Banner> actualizar(@PathVariable Long id, @RequestBody Banner banner) {
        Optional<Banner> actualizado = bannerService.actualizar(id, banner);
        return actualizado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Eliminar un banner
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (bannerService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
