package pe.edu.cibertec.dswiicl3carrascocastrosegundovicente.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UsuarioResponse {
    private Integer idusuario;
    private String nomusuario;
    private String token;

}
