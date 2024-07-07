package alura.api.foro.controller;

import alura.api.foro.domain.security.DatosAutenticacionUsuario;
import alura.api.foro.domain.security.DatosJWToken;
import alura.api.foro.service.AutenticacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticar")
public class AutenticacionController {

    @Autowired
    private AutenticacionService autenticacionService;

    @PostMapping("/login")
    public ResponseEntity<DatosJWToken> login(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacion){
        var token = autenticacionService.authenticate(datosAutenticacion);
        return ResponseEntity.ok(new DatosJWToken(token));
    }
}
