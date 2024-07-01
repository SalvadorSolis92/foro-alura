package alura.api.foro.controller;

import alura.api.foro.domain.topico.DatosRegistroTopico;
import alura.api.foro.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping("/topicos")
    public ResponseEntity<Map> registrarTopico(@RequestBody @Valid DatosRegistroTopico topico){
        System.out.println("respondiendo desde topicos");
        //this.topicoService.registrarTopico(topico);
        Map<String, String> response = new HashMap<>();
        response.put("mennsaje","seccess");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
