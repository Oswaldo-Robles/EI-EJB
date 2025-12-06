package pe.isil.dae1.ei.dao;

import java.util.List;


import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;
import pe.isil.dae1.ei.model.Usuario;

@Stateless
public class UsuarioDAO extends AbstractoDAO<Usuario> {

    public UsuarioDAO() {
    	super (Usuario.class);
    }

	// Método para validar usuario contra la base de datos: recibe usuario y clave y devuelve V/F
	public boolean validarUsuario(String email, String password) {
		TypedQuery<Usuario> q = em.createQuery(
				"SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password",
				Usuario.class);
		q.setParameter("email", email);
		q.setParameter("password", password);

		List<Usuario> listaUsuarios = q.getResultList();
		return !listaUsuarios.isEmpty();
	}
	
	 public int obtenerIdUsuario(Usuario usuario) {

	        TypedQuery<Usuario> q = em.createQuery(
	            "SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password",
	            Usuario.class
	        );
	        q.setParameter("email", usuario.getEmail());
	        q.setParameter("password", usuario.getPassword());

	        List<Usuario> lista = q.getResultList();

	        if (!lista.isEmpty()) {
	            return lista.get(0).getId();
	        }

	        return -1; // NO ENCONTRADO
	    }
	
}