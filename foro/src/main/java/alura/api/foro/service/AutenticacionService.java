package alura.api.foro.service;

import alura.api.foro.domain.usuario.Usuario;
import alura.api.foro.domain.security.DatosAutenticacionUsuario;
import alura.api.foro.domain.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String authenticate(DatosAutenticacionUsuario datosAutenticacion) throws AuthenticationException {

        var usuario = usuarioService.loadUserByUsername(datosAutenticacion.correo());

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        if (!bCryptPasswordEncoder.matches(datosAutenticacion.password(), usuario.getPassword())) {
            throw new AuthenticationException("Contraseña incorrecta") {};
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.correo(), datosAutenticacion.password());
        Authentication usuarioAutenticado = authenticationManager.authenticate(authenticationToken);
        return tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
    }

}