package alura.api.foro.service;

import alura.api.foro.domain.usuario.DatosUsuario;
import alura.api.foro.domain.curso.DatosCurso;
import alura.api.foro.domain.topico.DatosDetalleTopico;
import alura.api.foro.domain.topico.DatosRegistroTopico;
import alura.api.foro.domain.topico.DatosRespuestaTopico;
import alura.api.foro.domain.topico.Topico;
import alura.api.foro.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private UsuarioService usuarioService;

    public void registrarTopico(DatosRegistroTopico datosRegistroTopico) {
        var autor = usuarioService.obtenerAutorById(datosRegistroTopico.idAutor());
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

    public Page<DatosRespuestaTopico> listarTopicos(Pageable paginacion) {
        var topicosDB = this.topicoRepository.findAll(paginacion).map(DatosRespuestaTopico :: new);

        if(topicosDB == null){
            throw new RuntimeException("No se encontrarón resultados");
        }

        if (topicosDB.isEmpty()){
            throw new RuntimeException("No se han registrado topicos en el foro");
        }

        return  topicosDB;
    }

    public DatosDetalleTopico buscarTopicoById(Long id){
        var topicoDB = this.topicoRepository.findById(id);

        if (!topicoDB.isPresent()){
            throw new RuntimeException("No se encontró información del curso");
        }else{
            var topico = topicoDB.get();
            var autor = new DatosUsuario(topico.getAutor().getId(), topico.getAutor().getNombre(), topico.getAutor().getCorreoElectronico());
            var curso = new DatosCurso(topico.getCurso().getId(), topico.getCurso().getNombre(), topico.getCurso().getCategoria());

            return new DatosDetalleTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), autor, curso);
        }

    }

    public DatosRegistroTopico actualizarTopico(Long id, DatosRegistroTopico topicoUpdate) {

        if (this.topicoRepository.existsById(id)){
            var autor = usuarioService.obtenerAutorById(topicoUpdate.idAutor());
            var curso = cursoService.obtenerCursoById(topicoUpdate.idCurso());

            //valida si existe el curso
            if (!autor.isPresent()){
                throw new RuntimeException("No existe el autor");
            }

            //validar si existe el autor
            if (!curso.isPresent()) {
                throw new RuntimeException("No existe el autor");
            }

            Topico topicoDB = this.topicoRepository.findById(id).get();
            topicoDB.actualizar(topicoUpdate);
            return new DatosRegistroTopico(topicoDB.getTitulo(), topicoDB.getMensaje(), topicoDB.getAutor().getId(), topicoDB.getCurso().getId());

        }else{
            throw new RuntimeException("No se encontró registro del topico por actualizar");
        }

    }

    public void borrarTopico(Long id) {
        var topicoDelete = this.topicoRepository.findById(id);

        if (topicoDelete.isPresent()){
            topicoDelete.get().desactivarTopico();
        }else {
            throw new RuntimeException("No se encontró registro del topico por borrar");
        }
    }
}
