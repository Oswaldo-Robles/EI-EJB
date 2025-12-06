package pe.isil.dae1.ei.service.producto;

import jakarta.ejb.Local;
import pe.isil.dae1.ei.model.producto.Producto;
import java.util.List;

@Local
public interface ProductoServiceLocal {
	Producto obtenerPorNombre(String nombre);
    Producto obtenerPorCategoria(String categoria);
    Producto obtenerPorId(int idProducto);
    List<Producto> buscarPorCampo(String campo, String valor);
    int insertar(Producto obj);
    int actualizar(Producto obj);
    int eliminar(int idProducto);
    List<Producto> listar();
}
