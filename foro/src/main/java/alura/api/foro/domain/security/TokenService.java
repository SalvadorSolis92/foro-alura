package alura.api.foro.domain.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;

    public String generarToken(Authentication usuario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("foro alura")
                    .withSubject(usuario.getName())
                    .withClaim("usuario", usuario.getName())
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
                    .withIssuer("voll med")
                    .build()
                    .verify(token);

            verifierd = verifyJWT.getSubject();

        }catch (JWTCreationException e){
            throw new RuntimeException("error al validar token");
        }

        System.out.println(verifierd);
        if (verifierd == null){
            throw new RuntimeException("verifier invalido");
        }else {
            return verifierd;
        }

    }
}
