package alura.api.foro.controller;

import alura.api.foro.domain.topico.DatosDetalleTopico;
import alura.api.foro.domain.topico.DatosRegistroTopico;
import alura.api.foro.domain.topico.DatosRespuestaTopico;
import alura.api.foro.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping("/topicos")
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico topico) {
        this.topicoService.registrarTopico(topico);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/topicos")
    public ResponseEntity<Page<DatosRespuestaTopico>> listarTopicos(@PageableDefault(size = 10) Pageable paginacion) {
        var topicos = this.topicoService.listarTopicos(paginacion);
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/topicos/{id}")
    public ResponseEntity<DatosDetalleTopico> detalleTopico(@PathVariable Long id) {
        var topico = this.topicoService.buscarTopicoById(id);
        return ResponseEntity.ok(topico);
    }

    @PutMapping("/topicos/{id}")
    @Transactional
    public ResponseEntity<DatosRegistroTopico> actualzatTopico(@PathVariable Long id, @RequestBody @Valid DatosRegistroTopico topico) {
        var topicoUpdate = this.topicoService.actualizarTopico(id, topico);
        return ResponseEntity.ok(topicoUpdate);
    }

    @DeleteMapping("/topicos/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable Long id) {
        this.topicoService.borrarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
