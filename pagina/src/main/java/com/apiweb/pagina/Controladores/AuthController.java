package com.apiweb.pagina.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.apiweb.pagina.DTOs.JwtResponse;
import com.apiweb.pagina.DTOs.LoginRequest;
import com.apiweb.pagina.Entidades.Usuario;
import com.apiweb.pagina.Servicio.JwtService;
import com.apiweb.pagina.Servicio.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        Usuario usuario = usuarioService.buscarUsuario(user.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = jwtService.generateToken(usuario); // ← aquí el cambio importante


        // ✅ Devuelve token y username (como Swagger lo espera)
        return new JwtResponse(token, usuario.getUsername());
    }
}
