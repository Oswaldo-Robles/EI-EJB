package pe.isil.dae1.ei.dao.producto;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.TypedQuery;
import pe.isil.dae1.ei.dao.AbstractoDAO;
import pe.isil.dae1.ei.model.producto.Producto;

@Stateless
public class ProductoDAO extends AbstractoDAO<Producto> {
	//Este AbstractoDAO ya importa un CRUD general para todo el ProductoDAO

    public ProductoDAO() {
        super(Producto.class); // llama al constructor de AbstractoDAO
    }

 // Método específico para obtener un producto por categoría
    public Producto obtenerPorCategoria(String categoria) {
    	 TypedQuery<Producto> q = em.createQuery(
    	            "SELECT p FROM Producto p WHERE p.categoria = :categoria",
    	            Producto.class
    	        );
    	        q.setParameter("categoria", categoria);

    	        List<Producto> lista = q.getResultList();
    	        return lista.isEmpty() ? null : lista.get(0);
    }
    
    public Producto obtenerPorNombre(String nombre) {
        TypedQuery<Producto> q = em.createQuery(
            "SELECT p FROM Producto p WHERE p.nombre = :nombre",
            Producto.class
        );
        q.setParameter("nombre", nombre);

        List<Producto> lista = q.getResultList();
        return lista.isEmpty() ? null : lista.get(0);
    }

}