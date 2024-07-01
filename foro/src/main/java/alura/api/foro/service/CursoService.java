package alura.api.foro.service;

import alura.api.foro.domain.curso.Curso;
import alura.api.foro.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Boolean existCurso(Long id){
        //usando jpa
        return this.cursoRepository.existsById(id);
    }

    public Optional<Curso> obtenerCursoById(Long idCurso) {
        return this.cursoRepository.findById(idCurso);
    }
}
