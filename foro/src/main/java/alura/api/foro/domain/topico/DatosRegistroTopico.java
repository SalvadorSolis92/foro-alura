package alura.api.foro.domain.topico;

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
                this.titulo = titulo.toUpperCase();
                this.mensaje = mensaje.toUpperCase();
                this.idAutor = idAutor;
                this.idCurso = idCurso;
        }
}
