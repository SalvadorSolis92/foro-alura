package alura.api.foro.controller;

import alura.api.foro.domain.security.DatosAutenticacionUsuario;
import alura.api.foro.domain.security.DatosJWToken;
import alura.api.foro.service.AutenticacionService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
@Tag(name = "Autenticacion", description = "obtiene el token para el usuario asignado que da acceso al resto de endpoint")//para documentacion
public class AutenticacionController {

    @Autowired
    private AutenticacionService autenticacionService;

    @Operation(summary = "Autenticarse en la API", description = "Obtiene un token de autenticación con vigencia de 2 horas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "token jwt"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado, Credenciales invalidas")
    })
    @PostMapping("/login")
    public ResponseEntity<DatosJWToken> login(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacion){
        var token = autenticacionService.authenticate(datosAutenticacion);
        return ResponseEntity.ok(new DatosJWToken(token));
    }
}
