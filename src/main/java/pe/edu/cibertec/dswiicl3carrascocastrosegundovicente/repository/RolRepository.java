package pe.edu.cibertec.dswiicl3carrascocastrosegundovicente.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.dswiicl3carrascocastrosegundovicente.model.bd.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Rol findByNomrol(String nombrerol);
}
