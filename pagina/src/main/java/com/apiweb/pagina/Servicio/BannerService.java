package com.apiweb.pagina.Servicio;

import com.apiweb.pagina.Entidades.Banner;
import com.apiweb.pagina.Repositorios.BannerRepository;
import com.apiweb.pagina.Repositorios.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BannerService {
    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    // ✅ Obtener todos los banners de una empresa por ID
    public List<Banner> obtenerBannersPorEmpresa(Long empresaId) {
        return bannerRepository.findByEmpresa_Id(empresaId);
    }

    // ✅ Crear nuevo banner
    public Banner crear(Banner banner) {
        return bannerRepository.save(banner);
    }

    // ✅ Actualizar un banner
    public Optional<Banner> actualizar(Long id, Banner nuevoBanner) {
        return bannerRepository.findById(id).map(banner -> {
            banner.setUrl(nuevoBanner.getUrl());
            banner.setDescripcion(nuevoBanner.getDescripcion());
            banner.setEstaBanner(nuevoBanner.getEstaBanner());
            banner.setEmpresa(nuevoBanner.getEmpresa());
            return bannerRepository.save(banner);
        });
    }

    // ✅ Eliminar un banner por ID
    public boolean eliminar(Long id) {
        if (bannerRepository.existsById(id)) {
            bannerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ✅ Obtener un banner por ID
    public Optional<Banner> obtenerPorId(Long id) {
        return bannerRepository.findById(id);
    }
}
