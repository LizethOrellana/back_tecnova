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
        System.out.println(empresaDTO.getId()+"   AQUI ESTA EL ID");        if (empresaDTO.getId() != 0){
            // Modo edición

            Empresa existente = empresaRepository.findById(empresaDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

            existente.setNombre(empresaDTO.getNombre());
            existente.setLogo(empresaDTO.getLogo());
            existente.setMision(empresaDTO.getMision());
            existente.setVision(empresaDTO.getVision());
            existente.getBanners().addAll(empresaDTO.getBanners());
            if (empresaDTO.getBanners() != null) {
                Banner banner= new Banner();

                for (Banner b : empresaDTO.getBanners()) {

                    banner.setSecuencial(b.getSecuencial());
                    banner.setDescripcion(b.getDescripcion());
                    banner.setUrl(b.getUrl());
                    banner.setEstaBanner(b.getEstaBanner());
                    banner.setEmpresa(existente);
                    bannerRepository.save(banner);

                }

            }

            // Puedes actualizar los banners u otros campos si los estás pasando
            return empresaRepository.save(existente);
        }
        else{
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

    }

    @Transactional
    public Empresa updateBanner(Empresa empresaDTO) {
        if (empresaDTO.getId() != null) {
            Empresa existente = empresaRepository.findById(empresaDTO.getId())
                    .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

            // Actualizamos banners de forma inteligente
            if (empresaDTO.getBanners() != null) {
                for (Banner nuevo : empresaDTO.getBanners()) {
                    Optional<Banner> existenteBanner = existente.getBanners().stream()
                            .filter(b -> b.getSecuencial() != null && b.getSecuencial().equals(nuevo.getSecuencial()))
                            .findFirst();

                    if (existenteBanner.isPresent()) {
                        // Editar el banner existente
                        Banner b = existenteBanner.get();
                        b.setDescripcion(nuevo.getDescripcion());
                        b.setUrl(nuevo.getUrl());
                    } else {
                        // Agregar banner nuevo
                        nuevo.setEmpresa(existente); // muy importante mantener la relación
                        existente.getBanners().add(nuevo);
                    }
                }
            }

            return empresaRepository.save(existente);
        }

        return empresaDTO;
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
