package alura.api.foro.domain.curso;

import alura.api.foro.domain.Categoria;

public record DatosCurso(
        Long id,
        String nombre,
        Categoria categoria
) {
    public DatosCurso(Long id, String nombre, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }
}
