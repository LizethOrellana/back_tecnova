package com.apiweb.pagina.Repositorios;

import com.apiweb.pagina.Entidades.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apiweb.pagina.Entidades.Banner;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner,Long>{
    List<Banner> findByEmpresa(Empresa empresa);
    List<Banner> findByEmpresa_Id(Long empresaId);
}
