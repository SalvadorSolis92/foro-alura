package alura.api.foro.controller;

import alura.api.foro.domain.usuario.DatosRegistroUsuario;
import alura.api.foro.domain.usuario.DatosUpdateUsuario;
import alura.api.foro.domain.usuario.DatosUsuario;
import alura.api.foro.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuarios", description = "Administración del padrón de usuarios")//para documentacion
@SecurityRequirement(name = "bearer-key")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Registro de un usuario", description = "Registra a un nuevo usuario que podra usarse con un formulario")
    @PostMapping("/registrar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistro){
        usuarioService.registrarUsuario(datosRegistro);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Actualizar datos de un usuario", description = "Permite actualizar algunos datos de los usuarios, excepto contraseña")
    @PutMapping("/actualizar")
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosUpdateUsuario datos){
        var usuario = usuarioService.actualizarUsuario(datos);
        return ResponseEntity.ok(new DatosUpdateUsuario(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico()));
    }

    @Operation(summary = "Listar el padrón de usuarios", description = "Lista el padrón de usuarios con la información resumida")
    @GetMapping("/listar")
    public ResponseEntity<List<DatosUsuario>> listarUsuarios(){
        var listaUsuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(listaUsuarios);
    }

    @Operation(summary = "Borra a un usuario", description = "Borra el registro de un usuario por medio del id")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity borrarUsuario(@PathVariable Long id){
        usuarioService.borrarUsuario(id);
        return ResponseEntity.ok().build();
    }



}
