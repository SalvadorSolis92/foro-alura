package alura.api.foro.exception;

public record DatosDetalleError(
        int status,
        String error,
        String path,
        String message
) {
    public DatosDetalleError(int status, String error, String path, String message) {
        this.status = status;
        this.error = error;
        this.path = path;
        this.message = message;
    }
}
