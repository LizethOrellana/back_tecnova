package com.apiweb.pagina.Controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/verificar-cedula/{cedula}")
    public ResponseEntity<String> verificarCedula(@PathVariable String cedula) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorCedula(cedula);

        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get().getPregunta());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar-password/{cedula}")
    public ResponseEntity<Map<String, String>> actualizarPassword(
            @PathVariable String cedula,
            @RequestBody Map<String, String> body) {

        String nuevaPassword = body.get("nuevaPassword");

        boolean actualizado = usuarioService.actualizarPasswordPorCedula(cedula, nuevaPassword);

        Map<String, String> response = new HashMap<>();
        if (actualizado) {
            response.put("mensaje", "Contraseña actualizada exitosamente");
            return ResponseEntity.ok(response);
        } else {
            response.put("mensaje", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }





}
