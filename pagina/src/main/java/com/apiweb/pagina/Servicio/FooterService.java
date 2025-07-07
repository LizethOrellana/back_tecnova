package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Footer;
import com.apiweb.pagina.Repositorios.FooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FooterService {
    @Autowired
    private FooterRepository footerRepository;

    // ✅ Obtener todos los footers
    public List<Footer> obtenerTodos() {
        return footerRepository.findAll();
    }

    // ✅ Obtener un footer por ID
    public Optional<Footer> obtenerPorId(Long id) {
        return footerRepository.findById(id);
    }

    // ✅ Crear un nuevo footer
    public Footer crear(Footer footer) {
        return footerRepository.save(footer);
    }

    // ✅ Actualizar un footer existente
    public Optional<Footer> actualizar(Long id, Footer nuevoFooter) {
        return footerRepository.findById(id).map(footer -> {
            footer.setDescripcion(nuevoFooter.getDescripcion());
            footer.setAutores(nuevoFooter.getAutores());
            return footerRepository.save(footer);
        });
    }

    // ✅ Eliminar un footer por ID
    public boolean eliminar(Long id) {
        if (footerRepository.existsById(id)) {
            footerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
