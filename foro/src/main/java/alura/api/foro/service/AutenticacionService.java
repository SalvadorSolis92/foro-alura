package alura.api.foro.service;

import alura.api.foro.domain.security.DatosAutenticacionUsuario;
import alura.api.foro.domain.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String authenticate(DatosAutenticacionUsuario datosAutenticacion) throws AuthenticationException {

        var usuario = autorService.loadUserByUsername(datosAutenticacion.correo());

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        if (!bCryptPasswordEncoder.matches(datosAutenticacion.password(), usuario.getPassword())) {
            throw new AuthenticationException("Contraseña incorrecta") {};
        }

        var authenticationToken = new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword());

        return tokenService.generarToken(authenticationToken);
    }

}