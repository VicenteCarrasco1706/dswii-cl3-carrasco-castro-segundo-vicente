package pe.edu.cibertec.dswiicl3carrascocastrosegundovicente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.dswiicl3carrascocastrosegundovicente.model.bd.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByNomusuario(String nomusuario);
}
