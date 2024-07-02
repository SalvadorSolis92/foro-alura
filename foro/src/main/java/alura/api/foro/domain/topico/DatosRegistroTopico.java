package alura.api.foro.domain.topico;

import alura.api.foro.domain.autor.DatosAutor;
import alura.api.foro.domain.curso.DatosCurso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank(message = "el titulo no puede ser nulo")
        String titulo,
        @NotBlank(message = "el mensaje no puede estar vacio")
        String mensaje,
        @NotNull(message = "debe indicarse el autor")
        Long idAutor,
        @NotNull(message = "debe indicarse el curso")
        Long idCurso
) {
        public DatosRegistroTopico(String titulo, String mensaje, Long idAutor, Long idCurso) {
                this.titulo = titulo;
                this.mensaje = mensaje;
                this.idAutor = idAutor;
                this.idCurso = idCurso;
        }
}
