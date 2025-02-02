package alura.api.foro.domain.security;

import alura.api.foro.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Usuario usuario){
        try {
            System.out.println("api secret " + apiSecret);
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro_alura")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion())
                    .sign(algorithm);

        }catch (JWTCreationException e){
            System.err.println(e);
            throw new RuntimeException("Error en la autenticación");
        }
    }


    private Instant generarFechaExpiracion(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
    }

    public String getSubject(String token){
        String verifierd = null;

        if (token == null){
            throw new RuntimeException("El token es nulo");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            var verifyJWT =  JWT.require(algorithm)
                    .withIssuer("foro_alura")
                    .build()
                    .verify(token);

            verifierd = verifyJWT.getSubject();

        }catch (JWTCreationException e){
            throw new RuntimeException("error al validar token");
        }

        if (verifierd == null){
            throw new RuntimeException("verifier invalido");
        }else {
            return verifierd;
        }

    }
}
