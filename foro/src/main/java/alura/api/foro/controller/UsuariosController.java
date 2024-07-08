package alura.api.foro.controller;

import alura.api.foro.domain.usuario.DatosRegistroUsuario;
import alura.api.foro.domain.usuario.DatosUpdateUsuario;
import alura.api.foro.domain.usuario.DatosUsuario;
import alura.api.foro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistro){
        usuarioService.registrarUsuario(datosRegistro);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/actualizar")
    @Transactional
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosUpdateUsuario datos){
        usuarioService.actualizarUsuario(datos);
        return ResponseEntity.ok().build();
    }


}
