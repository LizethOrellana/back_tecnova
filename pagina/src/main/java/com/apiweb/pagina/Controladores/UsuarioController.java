package com.apiweb.pagina.Controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.apiweb.pagina.Entidades.Usuario;
import com.apiweb.pagina.Servicio.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario){
        return usuarioService.guardar(usuario);
    }

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    // Cambia la ruta para buscar por username (cadena)
    @GetMapping("/username/{username}")
    public Optional<Usuario> listarPorNombre(@PathVariable String username){
        return usuarioService.buscarUsuario(username);
    }

    // Mantén la búsqueda por secuencial en esta ruta
    @GetMapping("/{secuencial}")
    public Optional<Usuario> listarPorSecuencial(@PathVariable Long secuencial){
        return usuarioService.buscarPorSecuencialUsuario(secuencial);
    }

    @DeleteMapping("/{secuencial}")
    public void eliminar(@PathVariable Long secuencial){
        usuarioService.eliminar(secuencial);
    }

    @PutMapping("/{secuencial}")
    public Usuario editar(@PathVariable Long secuencial, @RequestBody Usuario usuario) {
        usuario.setSecuencial(secuencial);
        return usuarioService.guardar(usuario);
    }
}
