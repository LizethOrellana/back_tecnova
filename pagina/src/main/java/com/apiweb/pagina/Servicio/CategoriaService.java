package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Categoria;
import com.apiweb.pagina.Repositorios.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // ✅ Obtener todas las categorías
    public List<Categoria> obtenerTodas() {
        return categoriaRepository.findAll();
    }

    // ✅ Obtener una categoría por ID
    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    // ✅ Crear nueva categoría
    public Categoria crear(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    // ✅ Actualizar categoría existente
    public Optional<Categoria> actualizar(Long id, Categoria nuevaCategoria) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(nuevaCategoria.getNombre());
            categoria.setDescripcion(nuevaCategoria.getDescripcion());
            categoria.setEstado(nuevaCategoria.getEstado());
            return categoriaRepository.save(categoria);
        });
    }

    // ✅ Eliminar categoría por ID
    public Optional<Categoria> eliminar(Long id) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setEstado(false);
            return categoriaRepository.save(categoria);
        });
    }
}
