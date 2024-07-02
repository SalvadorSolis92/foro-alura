package alura.api.foro.service;

import alura.api.foro.domain.autor.DatosAutor;
import alura.api.foro.domain.curso.DatosCurso;
import alura.api.foro.domain.topico.DatosDetalleTopico;
import alura.api.foro.domain.topico.DatosRegistroTopico;
import alura.api.foro.domain.topico.DatosRespuestaTopico;
import alura.api.foro.domain.topico.Topico;
import alura.api.foro.exception.ForoExceptionHandler;
import alura.api.foro.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<DatosRespuestaTopico> listarTopicos() {
        var topicosDB = this.topicoRepository.findAll();

        if(topicosDB == null){
            throw new RuntimeException("No se encontrarón resultados");
        }

        if (topicosDB.isEmpty()){
            throw new RuntimeException("No se han registrado topicos en el foro");
        }

        return  topicosDB.stream().map( t -> new DatosRespuestaTopico(
                t.getTitulo(), t.getMensaje(), t.getFechaCreacion(),
                t.getStatus(), t.getAutor().getNombre(),
                t.getCurso().getNombre() )).toList();
    }

    public DatosDetalleTopico buscarTopicoById(Long id){
        var topicoDB = this.topicoRepository.findById(id);

        if (!topicoDB.isPresent()){
            throw new RuntimeException("No se encontró información del curso");
        }else{
            var topico = topicoDB.get();
            var autor = new DatosAutor(topico.getAutor().getId(), topico.getAutor().getNombre(), topico.getAutor().getCorreoElectronico());
            var curso = new DatosCurso(topico.getCurso().getId(), topico.getCurso().getNombre(), topico.getCurso().getCategoria());

            return new DatosDetalleTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), autor, curso);
        }

    }
}
