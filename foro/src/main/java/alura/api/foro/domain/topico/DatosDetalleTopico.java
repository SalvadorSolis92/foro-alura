package alura.api.foro.domain.topico;

import alura.api.foro.domain.autor.DatosAutor;
import alura.api.foro.domain.curso.DatosCurso;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDateTime fechaCreacion,
        Boolean status,
        DatosAutor autor,
        DatosCurso curso
) {
}
