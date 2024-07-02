package alura.api.foro.domain;

public enum Categoria {
    PROGRAMACION("Cursos relacionados con programación y desarrollo de software"),
    DISENIO("Cursos de diseño gráfico y UI/UX"),
    NEGOCIOS("Cursos sobre administración y gestión de negocios"),
    MARKETING("Cursos de marketing digital y estrategias"),
    CIENCIA_DATOS("Cursos de ciencia de datos y análisis");

    private final String descripcion;

    // Constructor del enum
    Categoria(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método para obtener la descripción de la categoría
    public String getDescripcion() {
        return descripcion;
    }

}
