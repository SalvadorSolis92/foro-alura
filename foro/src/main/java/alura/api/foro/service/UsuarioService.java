package alura.api.foro.service;

import alura.api.foro.domain.usuario.DatosRegistroUsuario;
import alura.api.foro.domain.usuario.DatosUpdateUsuario;
import alura.api.foro.domain.usuario.DatosUsuario;
import alura.api.foro.domain.usuario.Usuario;
import alura.api.foro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> obtenerAutorById(Long idAutor) {
        return usuarioRepository.findById(idAutor);
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findBycorreoElectronico(correo);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return usuario;
    }


    public Usuario registrarUsuario(DatosRegistroUsuario datosRegistro) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        var passwordBCrypt = bCryptPasswordEncoder.encode(datosRegistro.password());
        var usuarioOb = new Usuario(null, datosRegistro.nombre(), datosRegistro.correo(), passwordBCrypt);

        Usuario usuario = usuarioRepository.save(usuarioOb);

        if (usuario == null) {
            throw new RuntimeException("Ocurrio un error en el registro");
        }
        return usuario;

    }

    public void actualizarUsuario(DatosUpdateUsuario datos) {
        var usuario = usuarioRepository.findById(datos.id());

        if (!usuario.isPresent()) {
            throw new RuntimeException("No se enontro al usuario");
        }

        var usuarioUpdate = usuario.get();

        usuarioUpdate.actualizarInfo(datos);

    }
}
