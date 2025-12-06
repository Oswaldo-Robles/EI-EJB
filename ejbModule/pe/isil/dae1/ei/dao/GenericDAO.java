package pe.isil.dae1.ei.dao;

import java.util.List;

public interface GenericDAO<T> {
	
	T obtenerPorId(int id);
	T insertar(T objeto);
	T actualizar(T objeto);
	int eliminar(int id);
	List<T> listar();
	
}
