package alura.api.foro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ForoExceptionHandler{

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DatosDetalleError> handleAllExceptions(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String error = status.getReasonPhrase();
        String path = request.getDescription(false).substring(4);
        String message = ex.getMessage();

        DatosDetalleError errorDetails = new DatosDetalleError(status.value(), error, path, message);

        return new ResponseEntity<>(errorDetails, status);
    }
}
