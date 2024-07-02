package alura.api.foro.domain.topico;

import alura.api.foro.domain.autor.Autor;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        String titulo,
        String mensaje,
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
}
