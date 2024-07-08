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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
    public Usuario actualizarUsuario(DatosUpdateUsuario datos) {
        var usuario = usuarioRepository.findById(datos.id());

        if (usuario.isPresent()) {
            var usuarioUpdate = usuario.get();
            return usuarioUpdate.actualizarInfo(datos);
        }else {
            throw new RuntimeException("User not found with id: " + datos.id());
        }

    }

    public List<DatosUsuario> listarUsuarios() {
        var usuarios = this.usuarioRepository.findAll();

        if (usuarios == null) {
            throw new RuntimeException("No se puedo listar el padrón de usuarios");
        }

        List<DatosUsuario> listaUsuarios = usuarios
                .stream()
                .map(u -> new DatosUsuario(u.getId(), u.getNombre(), u.getCorreoElectronico())).toList();

        return listaUsuarios;
    }

    @Transactional
    public void borrarUsuario(Long id) {
        var usuarioDelete = usuarioRepository.findById(id);

        if (usuarioDelete.isPresent()) {
            var usuario = usuarioDelete.get();
            usuarioRepository.delete(usuario);
        }else {
            throw new RuntimeException("No se encontró registro del usuario");
        }
    }
}
