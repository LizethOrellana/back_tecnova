package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Marca;
import com.apiweb.pagina.Entidades.Producto;
import com.apiweb.pagina.Entidades.TipoUsuario;
import com.apiweb.pagina.Repositorios.ProductoRepository;
import com.apiweb.pagina.Repositorios.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository tipousuarioRepository;

    // ✅ Obtener todos los productos
    public List<TipoUsuario> obtenerTodos() {
        return tipousuarioRepository.findAll();
    }

    // ✅ Obtener un producto por ID
    public Optional<TipoUsuario> obtenerPorId(Long id) {
        return tipousuarioRepository.findById(id);
    }

    // ✅ Crear nuevo producto
    public TipoUsuario crear(TipoUsuario tipoUsuario) {
        return tipousuarioRepository.save(tipoUsuario);
    }

    // ✅ Actualizar un producto existente
    public Optional<TipoUsuario> actualizar(Long id, TipoUsuario nuevotipoUsuario) {
        return tipousuarioRepository.findById(id).map(tipoUsuario -> {
            tipoUsuario.setNombre(nuevotipoUsuario.getNombre());
            return tipousuarioRepository.save(tipoUsuario);
        });
    }

    // ✅ Eliminar un producto por ID
    public Optional<TipoUsuario> eliminar(Long id) {
        return tipousuarioRepository.findById(id).map(tipoUsuario -> {
            tipoUsuario.setEstado(false);
            return tipousuarioRepository.save(tipoUsuario);
        });
    }

}
