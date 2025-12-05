package pe.isil.dae1.ei.dao.libro;

import java.util.List;

import jakarta.ejb.Stateless;

import jakarta.ejb.Stateless;

import pe.isil.dae1.ei.dao.AbstactDAO;
import pe.isil.dae1.ei.model.libro.Usuario;


@Stateless
public class UsuarioDAO extends AbstactDAO<Usuario> {

    public UsuarioDAO() {
        super(Usuario.class);
    }

    // LOGIN
    public Usuario autenticar(String email, String password) {
        String jpql = "SELECT u FROM Usuario u " +
                      "WHERE u.email = :email AND u.password = :pass";

        List<Usuario> lista = em.createQuery(jpql, Usuario.class)
                                .setParameter("email", email)
                                .setParameter("pass", password)
                                .getResultList();
        return lista.isEmpty() ? null : lista.get(0);
    }

    // VALIDAR PASSWORD ACTUAL (versión JPA)
    public boolean validarPasswordActual(int idUsuario, String passActual) {
        String jpql = "SELECT COUNT(u) FROM Usuario u " +
                      "WHERE u.id = :id AND u.password = :pass";
        Long cantidad = em.createQuery(jpql, Long.class)
                          .setParameter("id", idUsuario)
                          .setParameter("pass", passActual)
                          .getSingleResult();
        return cantidad != null && cantidad > 0;
    }

    // ACTUALIZAR PASSWORD (ya no usamos SP)
    public void actualizarPassword(int idUsuario, String nueva) {
        Usuario u = em.find(Usuario.class, idUsuario);
        if (u != null) {
            u.setPassword(nueva);
            // como es entidad administrada, se sincroniza al hacer commit
        }
    }
}
