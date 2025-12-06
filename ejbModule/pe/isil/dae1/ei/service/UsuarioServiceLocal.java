package pe.isil.dae1.ei.service;


import java.util.List;

import jakarta.ejb.Local;
import pe.isil.dae1.ei.model.Usuario;

@Local
public interface UsuarioServiceLocal {
	// Métodos que voy a preparar, y que quien tenga esta interfaz los tendrá que desarrollar
	boolean validarUsuario(Usuario usuario);
	int obtenerIdUsuario(Usuario usuario);
	Usuario obtenerPorId(int idUsuario);
	int insertar(Usuario usuario);
	int actualizar(Usuario usuario);
	int cambiarContrasena(int idUsuario, String passwordActual, String passwordNueva);
	int eliminar(int idUsuario);
	List<Usuario> listar();
}
