package alura.api.foro.service;

import alura.api.foro.domain.topico.DatosRegistroTopico;
import alura.api.foro.domain.topico.Topico;
import alura.api.foro.exception.ForoExceptionHandler;
import alura.api.foro.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private AutorService autorService;

    public void registrarTopico(DatosRegistroTopico datosRegistroTopico) {
        var autor = autorService.obtenerAutorById(datosRegistroTopico.idAutor());
        var curso = cursoService.obtenerCursoById(datosRegistroTopico.idCurso());

        //valida si existe el curso
        if (!autor.isPresent()){
            throw new RuntimeException("No existe el autor");
        }

        //validar si existe el autor
        if (!curso.isPresent()) {
            throw new RuntimeException("No existe el autor");
        }

        var nuevoTopico = new Topico(datosRegistroTopico, curso.get(), autor.get());
        this.topicoRepository.save(nuevoTopico);
    }
}
