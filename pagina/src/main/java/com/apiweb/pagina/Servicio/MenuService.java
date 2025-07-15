package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Menu;
import com.apiweb.pagina.Repositorios.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> obtenerTodos() {
        return menuRepository.findAll();
    }

    public Optional<Menu> obtenerPorId(Long id) {
        return menuRepository.findById(id);
    }

    public Menu crear(Menu menu) {
        return menuRepository.save(menu);
    }

    public Optional<Menu> actualizar(Long id, Menu nuevoMenu) {
        return menuRepository.findById(id).map(menu -> {
            menu.setNombre(nuevoMenu.getNombre());
            menu.setIcono(nuevoMenu.getIcono());
            menu.setRuta(nuevoMenu.getRuta());
            menu.setNivel_acceso(nuevoMenu.getNivel_acceso());
            menu.setActivo(nuevoMenu.isActivo());
            return menuRepository.save(menu);
        });
    }

    public boolean eliminar(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
