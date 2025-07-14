package com.apiweb.pagina.Servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apiweb.pagina.Entidades.Usuario;
import com.apiweb.pagina.Repositorios.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public Usuario guardar(Usuario usuario) {
        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        } else {
            // Si es edición y no se envió contraseña, recupera la original de la BD
            Usuario existente = usuarioRepository.findById(usuario.getSecuencial()).orElseThrow();
            usuario.setPassword(existente.getPassword());
        }

        return usuarioRepository.save(usuario);
    }


    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }
    public Optional<Usuario> buscarUsuario(String ussername){
        return usuarioRepository.findByUsername(ussername);
    }
    public Optional<Usuario> buscarPorSecuencialUsuario(Long secuencial){
        return usuarioRepository.findById(secuencial);
    }
    public void eliminar(Long secuecuencial){
        usuarioRepository.deleteById(secuecuencial);
    }
}