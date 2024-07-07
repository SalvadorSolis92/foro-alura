package alura.api.foro.service;

import alura.api.foro.domain.autor.Autor;
import alura.api.foro.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService implements UserDetailsService {

    @Autowired
    private AutorRepository autorRepository;

    public Boolean existeAutor(Long idAutor){
        return this.autorRepository.existsById(idAutor);
    }

    public Optional<Autor> obtenerAutorById(Long idAutor) {
        return this.autorRepository.findById(idAutor);
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        var usuario = autorRepository.findBycorreoElectronico(correo);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return usuario;
    }


}
