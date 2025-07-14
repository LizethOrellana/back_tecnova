package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Marca;
import com.apiweb.pagina.Repositorios.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    // ✅ Obtener todas las marcas
    public List<Marca> obtenerTodas() {
        return marcaRepository.findAll();
    }

    // ✅ Obtener una marca por ID
    public Optional<Marca> obtenerPorId(Long id) {
        return marcaRepository.findById(id);
    }

    // ✅ Crear nueva marca
    public Marca crear(Marca marca) {
        return marcaRepository.save(marca);
    }

    // ✅ Actualizar una marca existente
    public Optional<Marca> actualizar(Long id, Marca nuevaMarca) {
        return marcaRepository.findById(id).map(marca -> {
            marca.setNombre(nuevaMarca.getNombre());
            marca.setPaisOrigen(nuevaMarca.getPaisOrigen());
            marca.setEstado(nuevaMarca.getEstado());
            return marcaRepository.save(marca);
        });
    }

    public List<Marca> buscarPorNombre(String nombre) {
        return marcaRepository.findByNombreContainingIgnoreCase(nombre);
    }


    // ✅ Eliminar marca por ID
    public Optional<Marca> eliminar(Long id) {
        return marcaRepository.findById(id).map(marca -> {
            marca.setEstado(false);
            return marcaRepository.save(marca);
        });
    }
}
