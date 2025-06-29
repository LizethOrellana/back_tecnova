package com.apiweb.pagina.Servicio;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.apiweb.pagina.Entidades.Banner;
import com.apiweb.pagina.Entidades.Empresa;
import com.apiweb.pagina.Repositorios.BannerRepository;
import com.apiweb.pagina.Repositorios.EmpresaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final BannerRepository bannerRepository;

    @Transactional
    public Empresa saveFullEmpresa(Empresa empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setLogo(empresaDTO.getLogo());
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setVision(empresaDTO.getVision());
        empresa.setMision(empresaDTO.getMision());
       
        if (empresaDTO.getBanners() != null) {
            empresa.setBanners(empresaDTO.getBanners());
            for (Banner b : empresaDTO.getBanners()) {
                 Banner banner= new Banner();
                 banner.setSecuencial(b.getSecuencial());
                 banner.setDescripcion(b.getDescripcion());
                 banner.setUrl(b.getUrl());
                 banner.setEstaBanner(b.getEstaBanner());
                 banner.setEmpresa(empresa);
                 bannerRepository.save(banner);

            }
           
        }

        return empresaRepository.save(empresa);
    }

    public Optional<Empresa> findFirst() {
        return empresaRepository.findAll().stream().findFirst(); // o por ID fijo
    }

    public boolean deleteById(Long id) {
        if (empresaRepository.existsById(id)) {
            empresaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
