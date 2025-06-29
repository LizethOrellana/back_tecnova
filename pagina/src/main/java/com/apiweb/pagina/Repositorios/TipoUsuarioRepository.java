package com.apiweb.pagina.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiweb.pagina.Entidades.TipoUsuario;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoUsuarioRepository  extends JpaRepository<TipoUsuario,Long>{

    
}