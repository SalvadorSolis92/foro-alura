package alura.api.foro.controller;

import alura.api.foro.domain.usuario.DatosRegistroUsuario;
import alura.api.foro.domain.usuario.DatosUpdateUsuario;
import alura.api.foro.domain.usuario.DatosUsuario;
import alura.api.foro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/actualizar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosUpdateUsuario datos){
        var usuario = usuarioService.actualizarUsuario(datos);
        return ResponseEntity.ok(new DatosUpdateUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico()));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DatosUsuario>> listarUsuarios(){
        var listaUsuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(listaUsuarios);
    }



}
