package alura.api.foro.domain.autor;

public record DatosAutor(
        Long id,
        String nombre,
        String correoElectronico
) {

    public DatosAutor(Long id, String nombre, String correoElectronico) {
        this.id = id;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }
}
