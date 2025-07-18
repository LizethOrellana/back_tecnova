package com.apiweb.pagina.Servicio;


import com.apiweb.pagina.Entidades.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Clave secreta para firmar el token (debería estar en un config externo seguro)
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Extraer username del token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extraer cualquier claim usando una función
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

     private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())  // aquí usamos el método para obtener la Key
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Método para validar el token usando getClaims()
    public boolean validarToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Verifica si el token expiró
    private boolean isTokenExpired(String token) {
        final Date expiration = extractClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }
    private final String SECRET_KEY = "claveMuySecretaDeAlMenos256bitsClaveMuySecretaDeAlMenos256bits";

    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("secuencial", usuario.getSecuencial());
        claims.put("cedula",usuario.getCedula());
        claims.put("sub", usuario.getUsername());
        claims.put("nombre", usuario.getNombre());
        claims.put("apellido", usuario.getApellido());
        claims.put("telefono", usuario.getTelefono());
        claims.put("email", usuario.getUsername()); // si no usas email, puedes omitirlo
        claims.put("nivel_acceso", usuario.getTipoUsuario().getSecuencial());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
