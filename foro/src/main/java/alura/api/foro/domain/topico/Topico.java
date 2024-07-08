package alura.api.foro.domain.topico;

import alura.api.foro.domain.usuario.Usuario;
import alura.api.foro.domain.curso.Curso;
import alura.api.foro.domain.Respuesta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    @Column(nullable = false, unique = true)
    private String mensaje;

    private LocalDateTime fechaCreacion;

    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "")
    private List<Respuesta> respuestas;

    public Topico(DatosRegistroTopico datosRegistroTopico, Curso curso, Usuario autor) {
        this.titulo =  datosRegistroTopico.titulo().toUpperCase();
        this.mensaje = datosRegistroTopico.mensaje().toUpperCase();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.curso = curso;
        this.autor = autor;
    }

    public Topico actualizar(DatosRegistroTopico datos){
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        return this;
    }

    public void desactivarTopico() {
        this.status = false;
    }
}
