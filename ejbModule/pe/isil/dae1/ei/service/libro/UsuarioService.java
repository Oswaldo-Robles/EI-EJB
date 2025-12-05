package pe.isil.dae1.ei.service.libro;


import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.isil.dae1.ei.dao.libro.UsuarioDAO;
import pe.isil.dae1.ei.model.libro.Usuario;



@Stateless
@LocalBean
public class UsuarioService implements UsuarioServiceLocal {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario autenticar(String email, String password) {
        return usuarioDAO.autenticar(email, password);
    }

    @Override
    public boolean validarPasswordActual(int idUsuario, String passActual) {
        return usuarioDAO.validarPasswordActual(idUsuario, passActual);
    }

    @Override
    public void actualizarPassword(int idUsuario, String nueva) {
        usuarioDAO.actualizarPassword(idUsuario, nueva);
    }
}
