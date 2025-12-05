package pe.isil.dae1.ei.service.libro;

import java.util.List;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import pe.isil.dae1.ei.dao.libro.LibroDAO;
import pe.isil.dae1.ei.dao.libro.UsuarioDAO;
import pe.isil.dae1.ei.model.libro.Libro;
import pe.isil.dae1.ei.model.libro.Usuario;


@Stateless
@LocalBean
public class LibroService implements LibroServiceLocal {

    @Inject
    private LibroDAO libroDAO;
    
    @Inject
    private UsuarioDAO usuarioDAO;

    @Override
    public List<Libro> listar() {
        return libroDAO.listar();
    }

    @Override
    public List<Libro> buscarPorTituloAutor(String filtro) {
        return libroDAO.buscarPorTituloAutor(filtro);
    }
    
    @Override
    public Libro obtenerPorId(Integer id) {
        return libroDAO.obtenerPorId(id);
    }

    @Override
    public Libro insertar(Libro libro, Usuario usuarioSesion) {
        // Traemos una entidad Usuario gestionada por JPA
        Usuario uRef = usuarioDAO.obtenerPorId(usuarioSesion.getId());

        libro.setUsuarioCreacion(uRef);
        libro.setUsuarioActualizacion(uRef);

        return libroDAO.insertar(libro);
    }

    @Override
    public Libro actualizar(Libro libro, Usuario usuarioSesion) {
        Usuario uRef = usuarioDAO.obtenerPorId(usuarioSesion.getId());

        libro.setUsuarioActualizacion(uRef);

        return libroDAO.actualizar(libro);
    }



    @Override
    public void eliminar(int id) {
        libroDAO.eliminar(id);   
    }

}
