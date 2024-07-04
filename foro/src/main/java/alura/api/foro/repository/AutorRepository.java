package alura.api.foro.repository;

import alura.api.foro.domain.autor.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    Autor findBycorreoElectronico(String correo);
}
