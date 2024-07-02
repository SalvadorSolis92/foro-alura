package alura.api.foro.controller;

import alura.api.foro.domain.topico.DatosDetalleTopico;
import alura.api.foro.domain.topico.DatosRegistroTopico;
import alura.api.foro.domain.topico.DatosRespuestaTopico;
import alura.api.foro.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping("/registrar")
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico topico) {
        this.topicoService.registrarTopico(topico);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/listar")
    public ResponseEntity<List<DatosRespuestaTopico>> listarTopicos() {
        var topicos = this.topicoService.listarTopicos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<DatosDetalleTopico> detalleTopico(@PathVariable Long id) {
        var topico = this.topicoService.buscarTopicoById(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<DatosRegistroTopico> actualzatTopico(@PathVariable Long id, @RequestBody DatosRegistroTopico topico) {
        var topicoUpdate = this.topicoService.actualizarTopico(id, topico);
        return ResponseEntity.ok(topicoUpdate);
    }
}
