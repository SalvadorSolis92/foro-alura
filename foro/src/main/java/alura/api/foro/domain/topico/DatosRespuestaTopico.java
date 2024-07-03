package alura.api.foro.domain.topico;

import alura.api.foro.domain.autor.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDateTime fechaCreacion,
        Boolean status,
        String autor,
        String curso
) {
    public DatosRespuestaTopico(String titulo, String mensaje, LocalDateTime fechaCreacion, Boolean status, String autor, String curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.autor = autor;
        this.curso = curso;
    }

    public DatosRespuestaTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }
}
