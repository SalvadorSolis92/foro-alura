package alura.api.foro.service;

import alura.api.foro.domain.autor.Autor;
import alura.api.foro.domain.security.DatosAutenticacionUsuario;
import alura.api.foro.domain.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public String authenticateAndGenerateToken(DatosAutenticacionUsuario datosAutenticacion) throws AuthenticationException {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.correo(), datosAutenticacion.password());
        //System.out.println(authenticationToken.getName() + authenticationToken.getCredentials());
        var usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        //System.out.println(usuarioAutenticado.isAuthenticated());
        return this.tokenService.generarToken((Autor) usuarioAutenticado.getPrincipal());
    }

}
