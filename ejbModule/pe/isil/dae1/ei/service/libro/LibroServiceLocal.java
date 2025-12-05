package pe.isil.dae1.ei.service.libro;

import java.util.List;

import jakarta.ejb.Local;
import pe.isil.dae1.ei.model.libro.Libro;
import pe.isil.dae1.ei.model.libro.Usuario;


@Local
public interface LibroServiceLocal {

    List<Libro> listar();
    List<Libro> buscarPorTituloAutor(String filtro);
    Libro obtenerPorId(Integer id);
    Libro insertar(Libro libro, Usuario usuarioSesion);
    Libro actualizar(Libro libro, Usuario usuarioSesion);
    void eliminar(int id);
}