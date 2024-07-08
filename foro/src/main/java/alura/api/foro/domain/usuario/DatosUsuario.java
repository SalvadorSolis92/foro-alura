package alura.api.foro.domain.usuario;

public record DatosUsuario(
        Long id,
        String nombre,
        String correoElectronico
) {

    public DatosUsuario(Long id, String nombre, String correoElectronico) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }
}
