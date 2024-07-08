package alura.api.foro.domain.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosUpdateUsuario(
        @NotNull Long id,
        @NotBlank String nombre,
        @NotBlank @Email String correo
) {
}
