package alura.api.foro.exception;

import com.auth0.jwt.exceptions.TokenExpiredException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Ya existe un registro con esos datos");
        response.put("message", ex.getRootCause().getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<Object> handleTokenExpiredException(TokenExpiredException ex, WebRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Token expirado");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}
