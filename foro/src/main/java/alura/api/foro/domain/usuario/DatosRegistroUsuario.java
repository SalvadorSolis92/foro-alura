package alura.api.foro.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosRegistroUsuario(
        @NotBlank String nombre,
        @NotBlank String correo,
        @NotBlank @Size(min = 8, message = "la contrseña debe tener minimo 8 caracteres") String password
)
{
}
