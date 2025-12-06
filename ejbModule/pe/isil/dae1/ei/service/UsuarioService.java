package pe.isil.dae1.ei.service;

import java.util.List;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import pe.isil.dae1.ei.dao.UsuarioDAO;
import pe.isil.dae1.ei.model.Usuario;


@Stateless
@LocalBean
public class UsuarioService implements UsuarioServiceLocal {

    @Inject
    private UsuarioDAO usuarioDAO;

    public UsuarioService() {}

    // Validar usuario (login)
    public boolean validarUsuario(Usuario usuario) {
        return usuarioDAO.validarUsuario(
                usuario.getEmail(),
                usuario.getPassword()
        );
    }

    // Obtener ID por usuario/clave
    public int obtenerIdUsuario(Usuario usuario) {
        return usuarioDAO.obtenerIdUsuario(usuario);
    }

    // Obtener por ID
    public Usuario obtenerPorId(int idUsuario) {
        return usuarioDAO.obtenerPorId(idUsuario);
    }

    // Insertar
    public int insertar(Usuario usuario) {

        // Validar clave
        if (usuario.getPassword() == null || usuario.getPassword().length() < 6) {
            return -11; // error: muy corta
        }

        if (!tieneUnaLetraYNumero(usuario.getPassword())) {
            return -12; // error: no cumple requisitos
        }

        Usuario respuesta = usuarioDAO.insertar(usuario);

        if (respuesta != null) {
            return respuesta.getId();
        } else {
            return -12;
        }
    }

    // Actualizar
    public int actualizar(Usuario usuario) {

        if (usuario.getPassword() == null || usuario.getPassword().length() < 6) {
            return -11;
        }

        if (!tieneUnaLetraYNumero(usuario.getPassword())) {
            return -12;
        }

        Usuario respuesta = usuarioDAO.actualizar(usuario);

        if (respuesta != null) {
            return respuesta.getId();
        } else {
        	return -99; // error inesperado en BD
        }
    }

    // Eliminar
    public int eliminar(int idUsuario) {
        return usuarioDAO.eliminar(idUsuario);
    }

    // Listar
    public List<Usuario> listar() {
        return usuarioDAO.listar();
    }

    // Auxiliar: validar que la clave tenga letra + número
    private boolean tieneUnaLetraYNumero(String clave) {
        boolean tieneLetra = false;
        boolean tieneNumero = false;

        for (char c : clave.toCharArray()) {
            if (Character.isLetter(c)) tieneLetra = true;
            if (Character.isDigit(c)) tieneNumero = true;
        }

        return tieneLetra && tieneNumero;
    }

    @Override
    public int cambiarContrasena(int idUsuario, String claveActual, String claveNueva) {

        Usuario usuario = usuarioDAO.obtenerPorId(idUsuario);

        if (usuario == null) {
            return -1;
        }

        if (!usuario.getPassword().equals(claveActual)) {
            return -2;
        }

        if (claveNueva.length() < 6) {
            return -11;
        }

        if (!tieneUnaLetraYNumero(claveNueva)) {
            return -12;
        }

        usuario.setPassword(claveNueva);
        usuarioDAO.actualizar(usuario);

        return 1;
    }

}