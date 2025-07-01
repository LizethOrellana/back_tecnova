package com.apiweb.pagina.Repositorios;

import com.apiweb.pagina.Entidades.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
