package alura.api.foro.domain.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosAutenticacionUsuario(
        @NotNull
        @NotBlank
        String correo,
        @NotNull
        @NotBlank
        String password
) {
}
