package pe.isil.dae1.ei.service.libro;

import jakarta.ejb.Local;
import pe.isil.dae1.ei.model.libro.Usuario;


@Local
public interface UsuarioServiceLocal {

    Usuario autenticar(String email, String password);

    boolean validarPasswordActual(int idUsuario, String passActual);

    void actualizarPassword(int idUsuario, String nueva);
}
