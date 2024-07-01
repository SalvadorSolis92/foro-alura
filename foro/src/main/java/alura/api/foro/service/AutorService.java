package alura.api.foro.service;

import alura.api.foro.domain.autor.Autor;
import alura.api.foro.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public Boolean existeAutor(Long idAutor){
        return this.autorRepository.existsById(idAutor);
    }

    public Optional<Autor> obtenerAutorById(Long idAutor) {
        return this.autorRepository.findById(idAutor);
    }
}
